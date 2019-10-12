package com.mynews.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mynews.entity.MyNewsComment;




@Mapper
public interface MyNewsCommentDao {


	
	/**
	 * 根据文章标题更新文章表的评论数
	 * @param title
	 * @return
	 */
	int updateCount(@Param("title")String title);
	/**
	 * 基于评论id删除评论
	 * @param 评论id
	 * @return 影响的行数
	 */
	int deleteObjectById(@Param("id")Integer id);
	
	/**
	 * 基于评论id查询用户
	 * @param id 评论id
	 * @return 用户User
	 */
	String getCreatUser(@Param("id")Integer id);
	
	/**
	 * 基于评论id获取文章id，根据文章id获取用户名id,根据用户id获取用户名
	 * @param id 评论id
	 * @return String 用户
	 */
	String getArticleUser(@Param("id")Integer id);
	
	/**
	 * 根据文章id显示相关评论
	 * @return 所有评论
	 */
	List<MyNewsComment> listComments(@Param("title")String title);
	
	/**===========================================添加评论====================================================*/
	/**
	 * 将用户评论写入到数据库
	 * @param comment 评论内容
	 * @return
	 */
	int insertObject(MyNewsComment comment);
	
	/**
	 * 根据文章标题查询文章id
	 * @param title
	 * @return 文章id
	 */
	int findArticleIdByTitle(@Param("title")String title);
	
	/**
	 * 根据文章标题修改评论数
	 * @param articleName
	 * @return
	 */
	int updateArticleCommentCount(@Param("title")String title);
/**-------------------------------------------------------------------------------------------*/
	/**
	 * 根据用户id获取用户名
	 * @param id
	 * @return
	 */
	String findCreatedUserById(@Param("id")Integer id) ;

}
