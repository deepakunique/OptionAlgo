package com.optionAlgo.dao.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.optionAlgo.dao.DataDownloadDao;
import com.optionAlgo.dao.MasterDao;
import com.optionAlgo.entity.Expiry;
import com.optionAlgo.entity.OptionBean;
import com.optionAlgo.form.data.FutureDetailsFormDto;
import com.optionAlgo.form.data.FutureScripFormData;
import com.optionAlgo.form.data.OptionPriceDetailFormDto;
import com.optionAlgo.form.data.OptionPriceFormDto;
import com.optionAlgo.form.data.PositionDetailDto;
import com.optoinAlgo.utility.FutureScripDataBuilder;
import com.optoinAlgo.utility.OptionBeanBuilder;


@Transactional
@Repository
public class DataDownloadDaoImpl implements DataDownloadDao {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	
	
	
	/*@Override
	public Article getArticleById(int articleId) {
		return entityManager.find(Article.class, articleId);
	}*/
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map getRefreshFutureScrip() {
		
		Session session = entityManager.unwrap(Session.class);
		return FutureScripDataBuilder.downloadScripDataFromNse(session);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map getRefreshOptionBean() {
		
		Session session = entityManager.unwrap(Session.class);
		return OptionBeanBuilder.fetchAllOptionBeanData(session);
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllScripNames() {
		
		Query query = entityManager.createNativeQuery("select DISTINCT scripName from futurescrip order by scripName ");
		List<Object> scripNames = query.getResultList();
		List<String> scripList = new ArrayList<>();
				
		for(Object scrip : scripNames){
			scripList.add(scrip.toString());
			
		}
		
		return scripList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public FutureScripFormData getScripDataByScripName(String scripName) {
		
		Query query = entityManager.createNativeQuery("select ltp, expirydate,iv, ivp,"
				+ "	 percentChangeInOi, spotPrice, lotSize from futurescrip where scripName ='" +scripName+"'");
		
		
		FutureScripFormData fs = new FutureScripFormData();	
		List<Object[]> scipExpiryList = query.getResultList();
		LinkedHashMap<String , FutureDetailsFormDto>futureDetailsFormDtoMap = new LinkedHashMap<>();
		FutureDetailsFormDto fDetailDto ;
		int index=0;
			
		for(Object[] obj : scipExpiryList){
			
			fDetailDto = new FutureDetailsFormDto();
			fDetailDto.setFuturePrice(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setExpiryDate(obj[index++].toString());
			fDetailDto.setIv(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setIvp(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setChangeInOi(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setSpotPrice(Double.parseDouble(obj[index++].toString()));
			Double lotsize = Double.parseDouble(obj[index++].toString());
			
			fDetailDto.setLotSize(lotsize.intValue());
			List<OptionPriceFormDto> optionPriceList = getOptionPriceDataForAllStrikeByExpiry(scripName,fDetailDto.getExpiryDate());
			fDetailDto.setOptionPricesList(optionPriceList);
			futureDetailsFormDtoMap.put(fDetailDto.getExpiryDate(), fDetailDto);
			index=0;
		}
		
		fs.setFutureAllExpiryMap(futureDetailsFormDtoMap);
		fs.setScripName(scripName);
		return fs;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OptionPriceFormDto> getOptionPriceDataForAllStrikeByExpiry(String scripName, String expiryDate) {
		
		LinkedHashMap<String,OptionPriceFormDto > optionPricesMap; 
		
		Query query = entityManager.createNativeQuery("select strikePrice, iv, ltp, optionType FROM OptionBean as ob where ob.seriesName ='"+ 
				expiryDate+"' and ob.scripName='"+scripName+"' ORDER BY ob.strikePrice");
		
		
		List<Object[]> optionBeanForExpiryList = query.getResultList();
		HashMap<String, OptionPriceDetailFormDto> optionTypePriceMap;
		int index=0;
		OptionPriceFormDto opDto;
		
		Map<Double, OptionPriceFormDto> tempMap = new HashMap<>();
		OptionPriceDetailFormDto opDetailDto;
		String optionType;
		Double strikePrice;
		for(Object[] obj : optionBeanForExpiryList){
			
			opDetailDto = new OptionPriceDetailFormDto();
			
			strikePrice = Double.parseDouble(obj[index++].toString());
			opDto = tempMap.get(strikePrice);
			if(opDto == null)
				opDto= new OptionPriceFormDto();
			
			opDto.setStrike(strikePrice);
			
			opDetailDto.setIv(Double.parseDouble(obj[index++].toString()));
			opDetailDto.setPrice(Double.parseDouble(obj[index++].toString()));
			optionType =obj[index++].toString();
			
			opDto.getOptionTypePriceMap().put(optionType, opDetailDto);
			index=0;
			tempMap.put(strikePrice, opDto);
		}
		
		return new ArrayList<OptionPriceFormDto>(tempMap.values()); 
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Double getCurrentPriceForPosition(PositionDetailDto po, String scripName) {
		
		
		Query query =null;
		if(po.getInstrumentType().equals("F")){
			query = entityManager.createNativeQuery("select ltp "
					+ "	from futurescrip where scripName ='" +scripName+"' and expiryDate = '"+ po.getExpiryDate()+"'");
		} else{
			 
			query = entityManager.createNativeQuery("select ltp FROM OptionBean as ob where ob.seriesName ='"+ 
				po.getExpiryDate()+"' and ob.scripName='"+scripName+"' and ob.optionType = '"+po.getOptionType()+  "' and ob.strikePrice = " + po.getStrikePrice());
			
		}
		
		
		List<Object[]> scipExpiryList = query.getResultList();
		
		Double ltp =0.0;
		for(Object obj : scipExpiryList){
			ltp =Double.parseDouble(obj.toString());
		}
		return ltp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Double> getStrikePriceByScripNameLimit(String scripName,int noOfStrikePrice, boolean itm, Double spotPrice,String seriesName) {
		
		
		Query query =null;
		if(itm){
			query = entityManager.createNativeQuery("SELECT DISTINCT ob.strikePrice FROM OptionBean as ob WHERE ob.scripName = '" 
					+scripName+"' and ob.seriesName = '"+seriesName + "' and ob.strikePrice < "+ spotPrice
					+ " ORDER BY strikePrice DESC  LIMIT "+ noOfStrikePrice);
		} else{
			 
			query = entityManager.createNativeQuery("SELECT DISTINCT ob.strikePrice FROM OptionBean as ob WHERE ob.scripName = '" 
					+scripName+"' and ob.seriesName = '"+seriesName + "' and ob.strikePrice >= "+ spotPrice
					+ " ORDER BY strikePrice   LIMIT "+ noOfStrikePrice);
		}
		
		
		List<Object[]> spList = query.getResultList();
		Double sp;
		List<Double> strikePriceList= new ArrayList<>();
		for(Object obj : spList){
			sp =Double.parseDouble(obj.toString());
			strikePriceList.add(sp);
		}
		return strikePriceList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Double getSpotPriceByScrip(String scripName, String expiryDate) {
		
		
		Query query =null;
			query = entityManager.createNativeQuery("select ltp "
					+ "	from futurescrip where scripName ='" +scripName+"' and expiryDate = '"+ expiryDate+"'");
		
		
		
		List<Object[]> scipExpiryList = query.getResultList();
		
		Double ltp =0.0;
		for(Object obj : scipExpiryList){
			ltp =Double.parseDouble(obj.toString());
		}
		return ltp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, OptionBean> getOptionBeanByStrikePrice(String scripName, String expiryDate, List<Double>spList) {
		
		/*String strikePrice="";
		for(Double sp :spList){
			strikePrice=strikePrice+sp+" ,";
		}
		strikePrice = strikePrice.substring(0, strikePrice.length()-2);
		Query query =null;
			query = entityManager.createNativeQuery("select * "
					+ "	from OptionBean where scripName ='" +scripName+"' and expiryDate = '"+ expiryDate+"' "
							+ "and ob.strikePrice in ("+strikePrice+")");*/
		
		
		
			
			String hql = "FROM OptionBean as ob where ob.scripName = :scrip  and ob.seriesName = :series and ob.strikePrice in (:strike)";
			Query query =  entityManager.createQuery(hql);
			query.setParameter("scrip", scripName);
			query.setParameter("series", expiryDate);
			query.setParameter("strike", spList);
			List<OptionBean> obList = query.getResultList();
			Map<String, OptionBean> obMap = new HashMap<>();
			for(OptionBean ob : obList){
				obMap.put(ob.getStrikePrice()+ob.getOptionType(), ob);
			}
			
			
		return obMap;
	}
	
	/*@Override
	public void addArticle(Article article) {
		entityManager.persist(article);
	}
	@Override
	public void updateArticle(Article article) {
		Article artcl = getArticleById(article.getArticleId());
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		entityManager.flush();
	}
	@Override
	public void deleteArticle(int articleId) {
		entityManager.remove(getArticleById(articleId));
	}
	@Override
	public boolean articleExists(String title, String category) {
		String hql = "FROM Article as atcl WHERE atcl.title = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean getRefreshFutureByScripName(String scripName) {
		
		Session session = entityManager.unwrap(Session.class);
		boolean result = FutureScripDataBuilder.downloadScripDataFromNseByScripName(session, scripName);
		result = OptionBeanBuilder.fetchOptionBeanDataByScripName(session,scripName);
		return result;
	}
}
