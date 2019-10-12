package com.tt.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.tt.common.annotation.RequiredLog;
import com.tt.common.exception.ServiceException;
import com.tt.common.vo.PageObject;
import com.tt.news.dao.NewsUserDao;
import com.tt.news.entity.NewsUser;
import com.tt.news.service.NewsUserService;

@Service
public class NewsUserServiceImpl implements NewsUserService {
	@Autowired
	private NewsUserDao newsUserDao;
	@Override
	@RequiredLog("用户查看")
	public List<NewsUser> getUser(){
		List<NewsUser> ulist = newsUserDao.getUser();
		return ulist;
	}
	@Override
	public PageObject<NewsUser> findPageObjects(
			String name, Integer pageCurrent) {
		//1.验证参数合法性
		//1.1验证pageCurrent的合法性，
		//不合法抛出IllegalArgumentException异常
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		//2.基于条件查询总记录数
		//2.1) 执行查询
		int rowCount=newsUserDao.getRowCount(name);
		//2.2) 验证查询结果，假如结果为0不再执行如下操作
		if(rowCount==0)
			throw new ServiceException("系统没有查到对应记录");
		//3.基于条件查询当前页记录(pageSize定义为2)
		//3.1)定义pageSize
		int pageSize=3;
		//3.2)计算startIndex
		int startIndex=(pageCurrent-1)*pageSize;
		//3.3)执行当前数据的查询操作
		List<NewsUser> records=
				newsUserDao.findPageObjects(name, startIndex, pageSize);
		//4.对分页信息以及当前页记录进行封装
		//4.1)构建PageObject对象
		PageObject<NewsUser> pageObject=new PageObject<>();
		//4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
		//5.返回封装结果。
		return pageObject;
	}
	
	@Override
	public int roleIdById(Integer roleId, Integer id) {
		if(id==null||id<=0)
			throw new IllegalArgumentException("参数不合法，id="+id);
		if(roleId==null||(roleId!=1&&roleId!=2&&roleId!=3))
			throw new ServiceException("参数不合法，roleId="+roleId);
		int rows = 0;
		try {
			rows = newsUserDao.roleIdById(roleId, id);
		}catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException("系统正在维护");
		}
		if(rows==0)
			throw new ServiceException("此记录可能已经不存在");	
		return rows;
	}

}
