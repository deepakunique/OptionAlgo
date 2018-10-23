package com.optionAlgo.dao.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.optionAlgo.dao.MasterDao;
import com.optionAlgo.entity.Article;
import com.optionAlgo.entity.Expiry;
import com.optionAlgo.form.data.FutureDetailsFormDto;
import com.optionAlgo.form.data.FutureScripFormData;
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
			
		for(Object[] obj : scipExpiryList){
			
			fDetailDto = new FutureDetailsFormDto();
			fDetailDto.setFuturePrice(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setExpirdate(obj[index++].toString());
			fDetailDto.setIv(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setIvp(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setChangeInOi(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setSpotPrice(Double.parseDouble(obj[index++].toString()));
			Double lotsize = Double.parseDouble(obj[index++].toString());
			
			fDetailDto.setLotSize(lotsize.intValue());
			futureDetailsFormDtoMap.put(fDetailDto.getExpirdate(), fDetailDto);
			
			index=0;
		}
		
		fs.setFutureAllExpiryMap(futureDetailsFormDtoMap);
		fs.setScripName(scripName);
		return fs;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public LinkedHashMap<String, OptionPriceFormDto> getOptionPriceDataForAllStrikeByExpiry(String scripName, String expiryDate) {
		
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
			fDetailDto.setExpirdate(obj[index++].toString());
			fDetailDto.setIv(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setIvp(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setChangeInOi(Double.parseDouble(obj[index++].toString()));
			fDetailDto.setSpotPrice(Double.parseDouble(obj[index++].toString()));
			Double lotsize = Double.parseDouble(obj[index++].toString());
			
			fDetailDto.setLotSize(lotsize.intValue());
			futureDetailsFormDtoMap.put(fDetailDto.getExpirdate(), fDetailDto);
			
			index=0;
		}
		
		fs.setFutureAllExpiryMap(futureDetailsFormDtoMap);
		fs.setScripName(scripName);
		return fs;
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
