package com.optionAlgo.dao.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.optionAlgo.dao.MasterDao;
import com.optionAlgo.entity.Article;
import com.optionAlgo.entity.Expiry;
import com.optionAlgo.entity.OptionBean;
import com.optionAlgo.form.data.FutureDetailsFormDto;
import com.optionAlgo.form.data.FutureScripFormData;
import com.optionAlgo.form.data.OptionPriceDetailFormDto;
import com.optionAlgo.form.data.OptionPriceFormDto;


@Transactional
@Repository
public class MasterDaoImpl implements MasterDao {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	
	/*@Override
	public Article getArticleById(int articleId) {
		return entityManager.find(Article.class, articleId);
	}*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Expiry> getAllExpiries() {
		String hql = "FROM Expiry as exp ORDER BY exp.id";
		return (List<Expiry>) entityManager.createQuery(hql).getResultList();
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
		int count=0;
			
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
			futureDetailsFormDtoMap.put(count+++"", fDetailDto);
			index=0;
		}
		
		fs.setFutureAllExpiryMap(futureDetailsFormDtoMap);
		fs.setFutureAllExpiryMapSize(futureDetailsFormDtoMap.size());
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
}
