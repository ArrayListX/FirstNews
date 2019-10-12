package com.mynews.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.common.exception.ServiceException;
import com.common.vo.PageObject;
import com.mynews.dao.MyNewsCommentDao;
import com.mynews.entity.MyNewsComment;
import com.mynews.service.MyNewsCommentService;



@Service
public class MyNewsCommentServiceImpl implements MyNewsCommentService{
	
	@Autowired
	private MyNewsCommentDao myNewsCommentDao;
	
	/**基于评论id删除评论信息*/
	//标识作用,出现异常回滚
	@Transactional
	@Override
	public int deleteObjectById(Integer id,String title,String createdUser) {
		//根据评论id获取文章id，根据文章id获取文章用户名id,根据文章用户id获取文章用户名  
		String articleUser = myNewsCommentDao.getArticleUser(id);
		//根据评论id查询当前评论用户
		String user = myNewsCommentDao.getCreatUser(id);
		//获取当前系统登录用户
		//1.如果没有登录
		if(createdUser==null)
			throw new RuntimeException("您还没有登录，请先登录！");
		//判断要执行删除操作的用户(当前登录的用户)是否为评论发表者或者文章发表者
		if(user.equals(createdUser)||articleUser.equals(createdUser)) {
			//符合条件，进行删除
			int rows;
			try {
				//2.执行删除操作
				rows = myNewsCommentDao.deleteObjectById(id);
				System.out.println(rows);
				//文章表评论数更新
				myNewsCommentDao.updateCount(title);
				System.out.println(title);
			    } catch (Throwable e) {
				  e.printStackTrace();
					throw new ServiceException("系统故障,正在恢复中...");
				}
			//3.对结果进行验证
			if(rows==0)
				throw new ServiceException("评论已不存在!");
			//4.返回结果
			return rows;
		}else throw new ServiceException("您没有权限删除当前评论");
	
	}
	
	
	/**根据文章标题获取文章id，根据文章id获取评论信息*/
	@Override
	public List<MyNewsComment> listComments(String title) {
		List<MyNewsComment> result = myNewsCommentDao.listComments(title);
		return result;
	}
	/**===========================================添加评论====================================================*/
	/**
	 * 添加评论
	 * createdUser 评论作者
	 * articleId 文章id
	 * content 评论
	 */
	@Transactional
	public int saveObject(String content,String articleName,String createdUser) {
		
		//1.获取当前用户名
		if(createdUser==null || createdUser=="")
			throw new RuntimeException("您还没有登录，请先登录！");
		    	
		//2.文章标题
		int articleId = myNewsCommentDao.findArticleIdByTitle(articleName);//通过文章表查询文章id
		
		//创建实例
		MyNewsComment comment = new MyNewsComment();
		comment.setCreatedUser(createdUser);
		comment.setArticleId(articleId);
		comment.setContent(content);
		comment.setDeleteTime(new Date());//时间处理
		comment.setDeleteUser(createdUser);
		comment.setValid(1);
		
		//向表中传入数据
		int result = myNewsCommentDao.insertObject(comment);
		//文章的评论数+1
		myNewsCommentDao.updateArticleCommentCount(articleName);
		if(result == 0) 
			throw new RuntimeException("数据插入失败");
		return result;
	}



}
