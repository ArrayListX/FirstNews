package com.mynews.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mynews.common.annotation.RequiredLog;
import com.mynews.common.exception.ServiceException;
import com.mynews.common.vo.PageObject;
import com.mynews.dao.NewsCommentDao;
import com.mynews.entity.NewsComment;
import com.mynews.service.NewsCommentService;



@Service
public class NewsCommentServiceImpl implements NewsCommentService{

	@Autowired
	private NewsCommentDao newsCommentDao;
	
	/**基于评论id删除评论信息*/
	//标识作用,出现异常回滚
	@Transactional
	@Override
	@RequiredLog("删除评论")
	public int deleteObjectById(Integer id) {
		int rows;
		try {
			//2.执行删除操作
			rows = newsCommentDao.deleteObjectById(id);
		    } catch (Throwable e) {
			  e.printStackTrace();
				throw new ServiceException("系统故障,正在恢复中...");
			}
		//3.对结果进行验证
		if(rows==0)
			throw new ServiceException("记录已经不存在!");
		//4.返回结果
		return rows;
	}
	
	@Override
	/**
	 * 分页查询
	 */
	@RequiredLog("查看评论")
	public PageObject<NewsComment> findPageObjects(String createdUser, Integer pageCurrent) {
		//1.验证当前页码值是否合法
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数并进行校验
		int rowCount=newsCommentDao.getRowCount(createdUser);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.查询当前页记录
		Integer pageSize=15;	//当前页的页面大小
		Integer startIndex=(pageCurrent-1)*pageSize;
		List<NewsComment> records = newsCommentDao.findPageObjects(createdUser, startIndex, pageSize);
		//4.封装查询结果并返回
		return new PageObject<>(rowCount, records, pageCurrent, pageSize);
	}

	@Override
	public List<NewsComment> listObjects() {
		List<NewsComment> list = newsCommentDao.listObjects();
		return list;
	}

	/**
	 * 根据id查询评论
	 */
	@Override
	@RequiredLog("查询评论")
	public NewsComment findObjectById(Integer id) {
		if(id==null||id<1)
			throw new IllegalArgumentException("id值无效");
		
		NewsComment nc = newsCommentDao.findObjectById(id);
		if(nc==null)
			throw new ServiceException("记录可能已经不存在");
		return nc;
	}

}
