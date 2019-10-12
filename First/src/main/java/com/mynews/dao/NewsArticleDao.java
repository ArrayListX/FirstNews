package com.mynews.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mynews.entity.NewsArticle;
import com.mynews.entity.NewsUser;


@Mapper
public interface NewsArticleDao {
	
	int insertObject(NewsArticle entity);
	
	List<NewsArticle> findPageObjects(
			@Param("title") String title,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("title") String title);

	int deleteObjects(@Param("ids")Integer... ids);
	
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid);
	
	
	//根据id修改内容
	int updateObject(@Param("id")Integer id,@Param("typeId")Integer typeId,@Param("title")String title,@Param("content")String content);
	
	@Select("select * from news_article where id=#{id}")
	NewsArticle findObjectById(@Param("id")Integer id);
	
}
