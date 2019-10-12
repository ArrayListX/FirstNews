package com.mynews.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mynews.entity.NewsUser;


@Mapper
public interface NewsUserDao {
	/**
	 * 根据用户id进行修改
	 * 
	 */
	int updateUserById(@Param("username")String username,@Param("birthday")Date birthday ,@Param("mobile")String mobile);
	int insertObject(NewsUser entity);
	
	@Select("select * from news_user where username=#{username}")
	NewsUser findUserByUserName(String username);
	
	//根据username查找全部的值
	NewsUser findByUsername(@Param("username")String username);
	@Update("update news_user set password=#{password} where username=#{username}")
	int updatePasswordByUsername(@Param("username")String username,@Param("password")String password);
}
