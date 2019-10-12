package com.tt.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tt.common.annotation.RequiredLog;
import com.tt.common.exception.ServiceException;
import com.tt.common.vo.PageObject;
import com.tt.news.dao.NewsArticleDao;
import com.tt.news.entity.NewsArticle;
import com.tt.news.entity.NewsComment;
import com.tt.news.service.NewsArticleService;

@Service
public class NewsArticleServiceImpl implements NewsArticleService{

	@Autowired
	private NewsArticleDao newsArticleDao;

	@RequiredLog("查看文章")
	@Override
	public PageObject<NewsArticle> findPageObjects(String title, 
			Integer pageCurrent) {
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("参数不合法");
		//2.依据条件获取总记录数
		int rowCount=newsArticleDao.getRowCount(title);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.计算startIndex的值
		int pageSize=10;
		int startIndex=(pageCurrent-1)*pageSize;
		//4.依据条件获取当前页数据
		List<NewsArticle> records = newsArticleDao.findPageObjects(
				title, startIndex, pageSize);
		//5.封装数据
		PageObject<NewsArticle> pageObject=new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;
	}

	@RequiredLog("删除文章")
	@Override
	public int deleteObjects(Integer[] ids) {
		//1参数校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("请选择一个");
		//2执行删除
		int rows;
		try {
			rows = newsArticleDao.deleteObjects(ids);
		} catch (Throwable e) {
			e.printStackTrace();
			//发出报警信息
			throw new ServiceException("系统故障,正在恢复中...");
		}
		//对结果进行验证
		if(rows == 0)
			throw new ServiceException("记录可能已经不存在");

		return rows;
	}
	
	@RequiredLog("禁用启用")
	@Override
	public int validById(Integer id,
			Integer valid) {
		//1.合法性验证
		if(id==null||id<=0)
			throw new ServiceException("参数不合法,id="+id);
		if(valid!=1&&valid!=0)
			throw new ServiceException("参数不合法,valie="+valid);
		//2.执行禁用或启用操作
		int rows=0;
		try{
			rows=newsArticleDao.validById(id, valid);
		}catch(Throwable e){
			e.printStackTrace();
			//报警,给维护人员发短信
			throw new ServiceException("底层正在维护");
		}
		//3.判定结果,并返回
		if(rows==0)
			throw new ServiceException("此记录可能已经不存在");
		return rows;
	}
	
	@Override
	public NewsArticle findObjectById(Integer id) {
		if(id==null||id<1)
			throw new IllegalArgumentException("id值无效");
		
		NewsArticle nc = newsArticleDao.findObjectById(id);
		if(nc==null)
			throw new ServiceException("记录可能已经不存在");
		return nc;
	}

}