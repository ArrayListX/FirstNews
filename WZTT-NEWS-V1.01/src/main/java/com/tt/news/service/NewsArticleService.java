package com.tt.news.service;

import com.tt.common.vo.PageObject;
import com.tt.news.entity.NewsArticle;
import com.tt.news.entity.NewsComment;


public interface NewsArticleService {
	
	NewsArticle findObjectById(Integer id);
	
	PageObject<NewsArticle> findPageObjects(
			String title,
			Integer pageCurrent);

	int deleteObjects(Integer[] ids);
	
	int validById(Integer id,Integer valid);
	
}
