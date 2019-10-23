package com.mynews.service;

import com.mynews.common.vo.PageObject;
import com.mynews.entity.NewsArticle;
import com.mynews.entity.NewsUser;

public interface NewsArticleService {
	
	int saveObject(NewsArticle entity);
	
	
	
	PageObject<NewsArticle> findPageObjects(
			String title,
			Integer pageCurrent);

	int deleteObjects(Integer[] ids);
	
	int validById(Integer id,Integer valid);
	
	 //根据id修改用户信息
	 int updateArticleById(Integer id,Integer typeId,String title,String content);
	 //根据ID进行查询
	 NewsArticle findObjectById(Integer id);
	
}
