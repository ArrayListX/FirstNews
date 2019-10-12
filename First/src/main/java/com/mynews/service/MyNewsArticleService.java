package com.mynews.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.vo.PageObject;
import com.mynews.entity.MyNewsArticle;

public interface MyNewsArticleService {

	int addReadCount(Integer id);
	
	List<MyNewsArticle> findObject();
	
	MyNewsArticle findObjectsById(Integer id);
	
}
