package com.mynews.service;

import com.mynews.entity.NewsLog;

public interface NewsLogService {
	/**
	 * 将用户日志写入数据库
	 */
	int saveObject(NewsLog entity);
}
