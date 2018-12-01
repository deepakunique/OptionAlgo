package com.optoinAlgo.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.optionAlgo.entity.OptionBean;

/**
 * Hello world!
 *
 */
public class OptionBeanBuilder 
{
	private SessionFactory factory = null;
	
	public OptionBeanBuilder() {
	/*	// TODO Auto-generated constructor stub
		Configuration cg = new Configuration().configure("hibernate.cfg.xml");
				 //Configuration config = new Configuration().configure("annotations/hibernate.cfg.xml");
        cg.addAnnotatedClass(OptionBean.class);
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(cg.getProperties());
         factory = cg.buildSessionFactory(builder.buildServiceRegistry());*/
	}
	
	
	
    public static void main( String[] args )
    {
    	long x =System.currentTimeMillis();
    	
    	
    	OptionBeanBuilder bc = new OptionBeanBuilder();
        Session s= bc.factory.openSession();
        fetchAllOptionBeanData(s);
		
		System.out.println("Total time taken in seconds::" + (System.currentTimeMillis() - x)/1000);
		
    }

    public static boolean fetchOptionBeanDataByScripName(Session s, String scripName) {
 		boolean result = false;
			try{
				getScripSeriesData(s, scripName, AppConstant.currentSeries, AppConstant.nextSeries, null);
				result = true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return result;
	}
    

	public static Map fetchAllOptionBeanData(Session s) {
		List<String> failedScrip = new ArrayList<String>();
        List<String> successScrip = new ArrayList<String>();
        Map outCome = new HashMap<>();
        outCome.put("success", successScrip);
        outCome.put("failed", failedScrip);
		for(String scripLot : AppConstant.scripLotSizeArray){
			try{
				/*if(++count > 20){
					Thread.sleep(1000*20);
					count =0;1.17
				}*/
					
				
				StringTokenizer st = new StringTokenizer(scripLot,"|");
				String scripName = st.nextToken();
				int lotSize = Integer.parseInt(st.nextToken());
				getScripSeriesData(s, scripName, AppConstant.currentSeries, AppConstant.nextSeries, failedScrip);
				successScrip.add(scripName);
			}
			catch(Exception e){
						
					failedScrip.add(scripLot);
			}
		}
			
		System.out.println("Failed Scrip Count ::" + failedScrip.size() );
		
		ListIterator<String> itr = failedScrip.listIterator();
		int expCount =0;
		while(itr.hasNext()){
			
			try{
				StringTokenizer st = new StringTokenizer(itr.next(),"|");
				String scripName = st.nextToken();
				int lotSize = Integer.parseInt(st.nextToken());
				getScripSeriesData(s, scripName, AppConstant.currentSeries, AppConstant.nextSeries, failedScrip);
				itr.remove();
			}
			catch(Exception e){
				if(++expCount> 100)
					break;
				System.out.println("exception ::"); 
			}
		}
		return outCome;
	}

    
    
    
    

	private static void getScripSeriesData(Session s, String scripName, final String currentSeries,
			final String nextSeries,List<String> failedScrip) throws Exception {
		
		Set<Double> strikePriceSet = new HashSet<Double>();
		Map<String, Map<String, OptionBean>> obMap = new HashMap<String, Map<String, OptionBean>>();
		
		StringBuffer sb = new StringBuffer();
		String instrumentType = "OPTSTK";
		Transaction t = null;
		try {
			
			t = s.beginTransaction();
			
			if(scripName.contains("NIFTY")){
				
				instrumentType = "OPTIDX";
			}
			getURLAction(sb, AppConstant.charset,currentSeries,scripName, instrumentType);
			persistOptionBean(s, sb, currentSeries, obMap,strikePriceSet);
			
			sb= new StringBuffer();
			getURLAction(sb, AppConstant.charset,nextSeries,scripName,instrumentType);
			persistOptionBean(s, sb, nextSeries, obMap,strikePriceSet);
			t.commit();
			
			
		} catch(Exception e){
			
			e.printStackTrace();
			failedScrip.add(scripName);
			t.rollback();
			throw e;
		}
		
		
	}

	public static void persistOptionBean(Session s, StringBuffer sb, String seriesName,
			Map<String, Map<String, OptionBean>> obMap,Set<Double> strikePriceLSet) {
		Transaction t;
		org.jsoup.nodes.Document document = Jsoup.parse(sb.toString());
		org.jsoup.nodes.Element tableCMP = document.select("table").get(0);
		org.jsoup.nodes.Element cmpElement = tableCMP.select("b").get(0);
		org.jsoup.nodes.Node cmElement = cmpElement.childNode(0);
		StringTokenizer st = new StringTokenizer(cmElement.toString(), " ");
		String scripName =st.nextToken();
		double cmp =new Double(st.nextToken());
		
		org.jsoup.nodes.Element table = document.select("table").get(2);
		
		List<OptionBean> obList = new ArrayList<OptionBean>();
		
		org.jsoup.nodes.Element e2 = table.child(1);
		
		Elements optionRows = e2.select("tr");
		
		int rowSize = optionRows.size();
		int rowCount =0;
		for(org.jsoup.nodes.Element tr :  optionRows){
			
			if(++rowCount>=rowSize)
				break;
			
			Elements strikeRow = tr.select("td");
			
				int columnIndex= 1;
				OptionBean ob = new OptionBean();
				String optionType = "CE";
				Map<String, OptionBean> innerMap = new HashMap<String, OptionBean>();
				
				
				ob.setCmp(cmp);
				ob.setOi(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setChangeInOi(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setVolume(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setIv(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				
				
				org.jsoup.nodes.Element strikeRow1 = null;
				String value2 =null;
				
				if(strikeRow.get(columnIndex).getAllElements().get(0).select("a")!=null &&  
						strikeRow.get(columnIndex).getAllElements().get(0).select("a").size()>0){
					
					strikeRow1 =  strikeRow.get(columnIndex).getAllElements().get(0).select("a").get(0);
					
					 value2 = strikeRow1.childNode(0).toString().replaceAll(",", "");
					
					ob.setLtp(new Double(value2));
				}
				columnIndex++;
				ob.setNetChng(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setBidQty(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setBidPrice(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setAskPrice(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setAskQty(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				
				org.jsoup.nodes.Element strikeRow2 =  strikeRow.get(columnIndex++).getAllElements().get(0).select("b").first();
				
				ob.setStrikePrice(new Double(strikeRow2.childNode(0).toString().replaceAll(",", "")));
				ob.setOptionType(optionType);
				ob.setSeriesName(seriesName);
				ob.setScripName(scripName);
				obList.add(ob);
				
				innerMap.put(optionType, ob);
				
				
				double tempStrikePrice = ob.getStrikePrice();
				
			// Put Option
				ob = new OptionBean();
				optionType= "PE";
				ob.setCmp(cmp);
				ob.setBidQty(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setBidPrice(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setAskPrice(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setAskQty(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setNetChng(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				
				
				if(strikeRow.get(columnIndex).getAllElements().get(0).select("a")!=null &&  
						strikeRow.get(columnIndex).getAllElements().get(0).select("a").size()>0){
					
					strikeRow1 =  strikeRow.get(columnIndex).getAllElements().get(0).select("a").get(0);
					
					 value2 = strikeRow1.childNode(0).toString().replaceAll(",", "");
					
					ob.setLtp(new Double(value2));
				}
				columnIndex++;
				
				
				ob.setIv(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				
				ob.setVolume(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setChangeInOi(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setOi(new Double(parseValueFromColumn(strikeRow, columnIndex++)));
				ob.setOptionType(optionType);
				ob.setStrikePrice(tempStrikePrice);
				ob.setSeriesName(seriesName);
				ob.setScripName(scripName);
				
				innerMap.put(optionType, ob);
				
				obMap.put(String.valueOf(seriesName+"_"+ ob.getStrikePrice()), innerMap);
				strikePriceLSet.add(new Double(ob.getStrikePrice()));
				
				obList.add(ob);
		}
			 
		
		
       //t = s.beginTransaction();
        for(OptionBean o : obList){
       	 
       	 s.saveOrUpdate(o);
        }
       //t.commit();
	}
	
	public static String parseValueFromColumn(Elements strikeRow, int i) {
		String value;
		value = strikeRow.get(i).getAllElements().get(0).childNode(0).toString().replaceAll(",","");
		if(value.equalsIgnoreCase(" ") || value.equalsIgnoreCase("-"))
			value = "0";
		return value;
	}
	
	public static void getURLAction(StringBuffer sb, String charset,String series, String scripName, String instrumentType) {
		URLConnection connection;
		BufferedReader br = null;
		try {
			//connection = new URL("https://www.nseindia.com/live_market/"
				//	+ "dynaContent/live_watch/option_chain/optionKeys.jsp?symbolCode=-9999&symbol=NIFTY&symbol=BANKNIFTY&instrument=OPTIDX&date=-&segmentLink=17&segmentLink=17").openConnection();
			
			connection = new URL("https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?"
					+ "segmentLink=17&instrument="+instrumentType  + "&symbol="+scripName+"&date="+series).openConnection();
			
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + "UTF-8");
			
			/*try {
				
			    connection.getOutputStream().write("".getBytes(charset));
			}
			finally {
			    connection.getOutputStream().close();
			}*/
			String line="";
			connection.setReadTimeout(6000);
			InputStream response1 = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(response1));
			while ((line = br.readLine()) != null) {
				sb.append(line+"\n");
			}
			//System.out.println(sb.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
