package com.mynews.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mynews.entity.MyNewsArticle;

@Mapper
public interface MyNewsArticleDao {
	/**
	 * 阅读量
	 * @param id
	 * @return
	 */
	int addReadCount(@Param("id") Integer id);
	
	/**
	 * 显示所有新闻
	 * @param id
	 * @return
	 */
	List<MyNewsArticle> findObject(
			@Param("typeName") String typeName);
	
	/**
	 * 通过id查找文章，获取文章数值
	 * @param id
	 * @return
	 */
	MyNewsArticle findObjectsById(
			@Param("id") Integer id);
	/**
	 * 通过文章类别查找文章
	 */
	

}
