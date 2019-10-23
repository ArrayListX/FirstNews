package com.mynews.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mynews.entity.NewsLog;


@Mapper
public interface NewsLogDao {
	/**执行添加操作*/
	int insertObject(NewsLog entity); 
}
