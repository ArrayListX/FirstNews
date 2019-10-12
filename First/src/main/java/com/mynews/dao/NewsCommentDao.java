package com.mynews.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mynews.entity.NewsComment;


@Mapper
public interface NewsCommentDao {

	
	/**
	 * 基于条件查询总记录数
	 * @param createdUser 查询条件(例如查询哪个用户的评论信息)
	 * @return 总记录数(基于这个结果可以计算总页数)
	 */
	int getRowCount(@Param("createdUser")String createdUser);

	/**
	 * 基于评论id删除评论
	 * @param id评论id
	 * @return 影响的行数
	 */
	int deleteObjectById(@Param("id")Integer id);
	
	/**
	 * 基于条件id分页查询评论信息
	 * @param createdUser 查询条件（例如查询哪个用户的评论信息）
	 * @param startIndex 当前页的起始位置
	 * @param pageSize 当前页的页面大小
	 * @return 当前页的评论信息
	 */
	List<NewsComment> findPageObjects(@Param("createdUser")String createdUser,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);

	/**
	 * 列出所有评论
	 * @return 所有评论
	 */
	List<NewsComment> listObjects();
	
	/**
	 * 根据id查询评论
	 * @param id
	 * @return 一条评论
	 */
	@Select("select * from news_comment where id=#{id}")
	NewsComment findObjectById(@Param("id")Integer id);

}
