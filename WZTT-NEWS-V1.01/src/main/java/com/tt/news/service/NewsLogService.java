package com.tt.news.service;

import java.util.List;
import java.util.concurrent.Future;

import org.apache.ibatis.annotations.Param;
import com.tt.common.vo.PageObject;
import com.tt.news.entity.NewsLog;

public interface NewsLogService {
	/**
	 * 通过此方法实现分页查询操作
	 * @param username 基于条件查询时的参数名
	 * @param pageCurrent 当前页的页码值
	 * @return
	 */
	PageObject<NewsLog> findPageObjects(String username,Integer pageCurrent);
	
	/**
	 * 写用户行为日志到数据库
	 */
	int saveObject(NewsLog entity);
	
	/**根据查询记录*/
	List<NewsLog> findObjects();
	
}
