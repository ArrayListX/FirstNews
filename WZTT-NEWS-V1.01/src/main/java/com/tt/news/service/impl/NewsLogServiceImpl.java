package com.tt.news.service.impl;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.tt.common.exception.ServiceException;
import com.tt.common.vo.PageObject;
import com.tt.news.dao.NewsLogDao;
import com.tt.news.entity.NewsLog;
import com.tt.news.service.NewsLogService;

@Service
public class NewsLogServiceImpl implements NewsLogService{
	
	@Autowired
	private NewsLogDao newsLogDao;
	
	//分页查询
	@Override
	public PageObject<NewsLog> findPageObjects(String username, Integer pageCurrent) {
		//1、验证参数合法性
				//1.1验证pageCurrent的合法性
				//不合法抛出异常
				if(pageCurrent==null||pageCurrent<1)
					throw new IllegalArgumentException("当前页码不正确");//运行时异常，当前页码值无效时
				//2、基于条件查询总记录数
				//2.1执行查询
				int rowCount = newsLogDao.getRowCount(username);
				//2.2验证查询结果，假如结果为0不在执行如下操作
				if(rowCount==0) {
					throw new ServiceException("系统没有查到对应记录");
				}
				//3、基于条件查询当前页记录（pageSize定义为2）
				//3.1定义pageSize
				int pageSize=5;
				//3.2计算startIndex
				int startIndex=(pageCurrent-1)*pageSize;
				//3.3执行当前数据查询操作
				List<NewsLog> records=newsLogDao.findPageObjects(username, startIndex, pageSize);
				System.out.println(records.toString());
				//4.对分页信息以及当前页记录进行封装
				//4.1构建pageObject对象
				 PageObject<NewsLog> pageObject=new PageObject<NewsLog>();
				//4.2封装数据
				pageObject.setPageCurrent(pageCurrent);
			    pageObject.setPageSize(pageSize);
				pageObject.setRowCount(rowCount);
			    pageObject.setRecords(records);
		        pageObject.setPageCount((rowCount-1)/pageSize+1);
				//5返回封装结果
				return pageObject;
		}
	
		//保存日志
		@Override
		public int saveObject(NewsLog entity) {
			int rows = newsLogDao.insertObject(entity);
			return rows;
		}

		//根据ID查询记录
		@Override
		public List<NewsLog> findObjects() {
			List<NewsLog> list = newsLogDao.findObjects();
			return list;
		}
		
	
}
