package com.mynews.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mynews.common.annotation.RequiredLog;
import com.mynews.common.exception.ServiceException;
import com.mynews.common.vo.PageObject;
import com.mynews.dao.NewsArticleDao;
import com.mynews.entity.NewsArticle;
import com.mynews.entity.NewsUser;
import com.mynews.service.NewsArticleService;



@Service
public class NewsArticleServiceImpl implements NewsArticleService{

	@Autowired
	private NewsArticleDao newsArticleDao;

	@Override
	@RequiredLog("查看文章")
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
	
   //根据Id进行查找
	@Override
	public NewsArticle findObjectById(Integer id) {
		if(id==null||id<1)
			throw new IllegalArgumentException("id值无效");
		NewsArticle nc = newsArticleDao.findObjectById(id);
		if(nc==null)
			throw new ServiceException("记录可能已经不存在");
		return nc;
	}

	@Override
	public int saveObject(NewsArticle entity) {
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getTitle()))
			throw new ServiceException("标题不能为空");
		if(StringUtils.isEmpty(entity.getContent()))
			throw new ServiceException("内容不能为空");
		if(entity.getTypeId()==0)
			throw new ServiceException("必须为文章选择一个类别");		
		return newsArticleDao.insertObject(entity);
	}

	//根据id进行修改
	@Override
	public int updateArticleById(Integer id,Integer typeId,String title,String content) {
		int rows = newsArticleDao.updateObject(id,typeId,title,content);
		return rows;
	}

}