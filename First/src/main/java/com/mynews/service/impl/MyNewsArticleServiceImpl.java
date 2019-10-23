package com.mynews.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mynews.common.annotation.RequiredLog;
import com.mynews.common.exception.ServiceException;
import com.mynews.common.vo.JsonResult;
import com.mynews.common.vo.PageObject;
import com.mynews.dao.MyNewsArticleDao;
import com.mynews.entity.MyNewsArticle;
import com.mynews.service.MyNewsArticleService;


@Service
public class MyNewsArticleServiceImpl implements MyNewsArticleService{

	@Autowired
	private MyNewsArticleDao myNewsArticleDao;
	
	@Override
	@RequiredLog("查看新闻")
	public List<MyNewsArticle> findObject(String typeName) {
		List<MyNewsArticle> nc = myNewsArticleDao.findObject(typeName);
		if(nc==null)
			throw new ServiceException("记录可能已经不存在");
		return nc;
	}

	@Override
	public MyNewsArticle findObjectsById(Integer id) {
		MyNewsArticle list = myNewsArticleDao.findObjectsById(id);
		if(id == null||id==0) {
			throw new IllegalArgumentException("出现错误");
		}
		if(list==null)
			throw new ServiceException("记录可能已经不存在");
		return list;
	}

	@Override
	@RequiredLog("查看文章详细")
	public int addReadCount(Integer id) {
		return myNewsArticleDao.addReadCount(id);
	}


}