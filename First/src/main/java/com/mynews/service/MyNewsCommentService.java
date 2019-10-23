package com.mynews.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mynews.common.vo.PageObject;
import com.mynews.entity.MyNewsComment;

public interface MyNewsCommentService {
	/**根据id删除用户评论信息*/
	int deleteObjectById(Integer id,String title,String createdUser);

	/**
	 * 获取当前页的评论信息
	 * @param createdUser 查询条件(根据用户名称查询)
	 * @param pageCurrent  当前页页码
	 * @return 封装了当前页记录以及页码信息的对象
	 */
	/**根据文章标题获取文章id，根据文章id获取评论信息*/
	List<MyNewsComment> listComments(String title);
	
	/**===========================================添加评论====================================================*/
	/**
	 * 添加评论
	 * @param createdUser 评论作者
	 * @param content 评论内容
	 * @param articleId 文章id
	 * return 返回执行结果
	 */
	int saveObject(String content,String articleName,String createdUser);



	
	
}
