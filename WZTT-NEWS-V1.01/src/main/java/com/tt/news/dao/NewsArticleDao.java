package com.tt.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tt.news.entity.NewsArticle;
import com.tt.news.entity.NewsComment;
import com.tt.news.service.NewsArticleService;

@Mapper
public interface NewsArticleDao {
	
	@Select("select * from news_article where id=#{id}")
	NewsArticle findObjectById(@Param("id")Integer id);
	
	List<NewsArticle> findPageObjects(
			@Param("title") String title,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("title") String title);

	int deleteObjects(@Param("ids")Integer... ids);
	
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid);
}
