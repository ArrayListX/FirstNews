package com.mynews.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mynews.dao.NewsLogDao;
import com.mynews.entity.NewsLog;
import com.mynews.service.NewsLogService;

@Service
public class NewsLogServiceImpl implements NewsLogService {

	@Autowired
	private NewsLogDao newsLogDao;
	
	//保存日志
	@Override
	public int saveObject(NewsLog entity) {
		int rows = newsLogDao.insertObject(entity);
		return rows;
	}

}
