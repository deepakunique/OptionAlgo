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
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import com.optionAlgo.entity.FutureScrip;

/**
 * Hello world!
 *
 */
public class FutureScripDataBuilder 
{
	private SessionFactory factory = null;
	
	public FutureScripDataBuilder() {
		/*// TODO Auto-generated constructor stub
		Configuration cg = new Configuration().configure("hibernate.cfg.xml");
				 //Configuration config = new Configuration().configure("annotations/hibernate.cfg.xml");
       
        cg.addAnnotatedClass(FutureScrip.class);
       // cg.addAnnotatedClass(FutureScripData.class);
        
        
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(cg.getProperties());
         factory = cg.buildSessionFactory(builder.buildServiceRegistry());*/
		
	}
	
	
	
    public static void main( String[] args )
    {
    	long x =System.currentTimeMillis();
    	
    	
    	FutureScripDataBuilder bc = new FutureScripDataBuilder();
        Session s= bc.factory.openSession();
        downloadScripDataFromNse(s);
		System.out.println("Total time taken in seconds::" + (System.currentTimeMillis() - x)/1000);
		
    }



	public static Map downloadScripDataFromNse(Session s) {
		List<String> failedScrip = new ArrayList<String>();
        List<String> successScrip = new ArrayList<String>();
        Map outCome = new HashMap<>();
        outCome.put("success", successScrip);
        outCome.put("failed", failedScrip);
		for(String scripLot : AppConstant.scripLotSizeArray){
			try{
				/*if(++count > 20){
					Thread.sleep(1000*20);
					count =0;
				}*/
					
				
				StringTokenizer st = new StringTokenizer(scripLot,"|");
				String scripName = st.nextToken();
				int lotSize = Integer.parseInt(st.nextToken());
				getScripSeriesData(s, scripName, AppConstant.currentSeries,failedScrip);
				getScripSeriesData(s, scripName, AppConstant.nextSeries,failedScrip);
				
				successScrip.add(scripName);
			}
			catch(Exception e){
						
					failedScrip.add(scripLot);
			}
		}
		return outCome;
	}

	public static boolean downloadScripDataFromNseByScripName(Session s,String scripName) {
		boolean result = false;
		try{
				getScripSeriesData(s, scripName, AppConstant.currentSeries,null);
				getScripSeriesData(s, scripName, AppConstant.nextSeries,null);
				result = true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
		return result;
	}
    
    
    

    public static void getURLAction(StringBuffer sb, String charset,String series, String scripName, String instrumentType) throws Exception {
		URLConnection connection;
		BufferedReader br = null;
		try {
			//connection = new URL("https://www.nseindia.com/live_market/"
				//	+ "dynaContent/live_watch/option_chain/optionKeys.jsp?symbolCode=-9999&symbol=NIFTY&symbol=BANKNIFTY&instrument=OPTIDX&date=-&segmentLink=17&segmentLink=17").openConnection();
			 
			
			
			 // https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/ajaxFOGetQuoteJSON.jsp?underlying=INFY&instrument=FUTSTK&expiry=25OCT2018&type=SELECT&strike=SELECT
			connection = new URL("https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/ajaxFOGetQuoteJSON.jsp?underlying="+scripName+"&instrument="
						+instrumentType  + "&expiry="+series+"&type=SELECT&strike=SELECT").openConnection();
			
			connection.setRequestProperty("Referer", "https://www.nseindia.com/products/content/derivatives/equities/archieve_fo.htm");
			//connection.setDoOutput(true);
			//connection.setRequestProperty("Accept-Charset", "UTF-8");
			///connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + "UTF-8");
			
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
			throw new Exception();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
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
    
    
	private static boolean getScripSeriesData(Session s, String scripName, final String currentSeries,
			List<String> failedScrip) throws Exception{
		
		Set<Double> strikePriceSet = new HashSet<Double>();
		Map<String,LiveRate> liveRateMap = new HashMap<String, LiveRate>();
		boolean result= false;
		
		StringBuffer sb = new StringBuffer();
		String instrumentType = "FUTSTK";
		Transaction t = null;
		try {
			
			t = s.beginTransaction();
			
			if(scripName.contains("NIFTY")){
				
				instrumentType = "FUTIDX";
			}
			getURLAction(sb, AppConstant.charset,currentSeries,scripName, instrumentType);
			//ParsingUtility.persistOptionBean(s, sb, currentSeries, obMap,strikePriceSet,liveRateMap,lotSize);
			 		final JSONObject obj = new JSONObject(sb.toString().trim());
			    	final JSONArray data = obj.getJSONArray("data");
			    	final JSONObject futureData = data.getJSONObject(0);
			     
			    	FutureScrip fs = new FutureScrip();
			    	fs.setAnnualVolatility(futureData.getDouble("annualisedVolatility"));
			    	fs.setDailyVolatility(futureData.getDouble("dailyVolatility"));
			    	fs.setHigh(Double.parseDouble(futureData.getString("highPrice").replaceAll(",", "")));
			    	fs.setLotsize(futureData.getInt("marketLot"));
			    	fs.setMwpl(Double.parseDouble(futureData.getString("marketWidePositionLimits").replaceAll(",", "")));
			    	fs.setScripName(futureData.getString("underlying"));
			    	fs.setPercentChange(Double.parseDouble(futureData.getString("pChange").replaceAll(",", "")));
			    	fs.setOi(Double.parseDouble(futureData.getString("openInterest").replaceAll(",", "")));
			    	fs.setBid(Double.parseDouble(futureData.getString("buyPrice1").replaceAll(",", "")));
			    	fs.setAsk(Double.parseDouble(futureData.getString("sellPrice1").replaceAll(",", "")));
			    	
			    	fs.setOpen(Double.parseDouble(futureData.getString("openPrice").replaceAll(",", "")));
			    	fs.setPrevClose(Double.parseDouble(futureData.getString("prevClose").replaceAll(",", "")));
			    	fs.setExpiryDate(futureData.getString("expiryDate"));
			    	fs.setLow(Double.parseDouble(futureData.getString("lowPrice").replaceAll(",", "")));
			    
			    	fs.setChange(Double.parseDouble(futureData.getString("change").replaceAll(",", "")));
			    	fs.setPercentChangeInOI(Double.parseDouble(futureData.getString("pchangeinOpenInterest").replaceAll(",", "")));
			    	fs.setSpotPrice(Double.parseDouble(futureData.getString("underlyingValue").replaceAll(",", "")));
			    	fs.setClose(Double.parseDouble(futureData.getString("closePrice").replaceAll(",", "")));
			    	fs.setLtp(Double.parseDouble(futureData.getString("lastPrice").replaceAll(",", "")));
			    	fs.setChangeinOI(Double.parseDouble(futureData.getString("changeinOpenInterest").replaceAll(",", "")));
			    	
			      /// TODO lastUpdateTime  need to add () obj.getString("lastUpdateTime")
			     
			      
			    		s.saveOrUpdate(fs);
			    		t.commit();
			    		result= true;
		} catch(Exception e){
			
			t.rollback();
			e.printStackTrace();
			throw e;
		}
		
		return result;
		
		
	}

	
}
