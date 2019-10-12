package com.tt.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tt.news.entity.NewsUser;

@Mapper
public interface NewsUserDao {
	@Select("select * from news_user order by id desc")
	public List<NewsUser> getUser();
	/**
	 * 查询所有的用户的信息
	 */
	List<NewsUser> findPageObjects(@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 获取的用户数量
	 */
	int getRowCount(
			@Param("username") String username);
	/**
	 * 
	 * @param roleId
	 * @param id
	 * @return
	 */
	int roleIdById(
			@Param("roleId")Integer roleId,
			@Param("id") Integer id);
	
}
