package com.tt.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.common.exception.ServiceException;
import com.tt.common.vo.PageObject;
import com.tt.news.dao.NewsRoleDao;
import com.tt.news.entity.NewsRole;
import com.tt.news.service.NewsRoleService;

@Service
public class NewsRoleServiceImpl implements NewsRoleService{
	@Autowired
	private NewsRoleDao newsRoleDao;
	@Override
	public PageObject<NewsRole> findPageObjects(String name, Integer pageCurrent) {
		//1.验证参数合法性
		//1.1验证pageCurrent的合法性
		if(pageCurrent==null||pageCurrent<1) 
			throw new IllegalArgumentException("当前页码不正确");
		//基于条件查询总记录数
		int rowCount = newsRoleDao.getRowCount(name);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<NewsRole> records = newsRoleDao.findPageObjects(name, startIndex, pageSize);
		//对分页信息以及当前页记录进行封装
		PageObject<NewsRole> pageObject = new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;
	}
}
