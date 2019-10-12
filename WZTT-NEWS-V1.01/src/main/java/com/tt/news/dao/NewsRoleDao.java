package com.tt.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tt.news.entity.NewsRole;
@Mapper
public interface NewsRoleDao {
	/**
	 * 分页查询角色信息
	 * @param startIndex 上一页的结束位置
     * @param pageSize 每页要查询的记录数
	 */
	List<NewsRole> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 查询记录总数
	 */
	int getRowCount(@Param("name")String name);	
}
