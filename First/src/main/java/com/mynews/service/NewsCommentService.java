package com.mynews.service;

import java.util.List;

import com.common.vo.PageObject;
import com.mynews.entity.NewsComment;



public interface NewsCommentService {
	
	/**根据用户id删除用户评论信息*/
	int deleteObjectById(Integer id);

	/**
	 * 获取当前页的评论信息
	 * @param createdUser 查询条件(根据用户名称查询)
	 * @param pageCurrent  当前页页码
	 * @return 封装了当前页记录以及页码信息的对象
	 */
	PageObject<NewsComment> findPageObjects(String createdUser,Integer pageCurrent);

	/**
	 * 列出所有评论
	 * @return
	 */
	List<NewsComment> listObjects();
	
	/**
	 * 根据id查询评论
	 */
	NewsComment findObjectById(Integer id);

}
