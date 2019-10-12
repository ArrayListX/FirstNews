package com.mynews.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.common.vo.PageObject;

import com.mynews.entity.NewsUser;
public interface NewsUserService {
	 //根据id修改用户信息
	  int updateUserById(String username,Date birthday,String mobile);
		NewsUser findUserByUserName(String username);
		int insertObject(NewsUser entity);
		void emailCode(String email);
		boolean docode(String emailcode);
	//根据username查找全部的值
	 Map<String, Object> findByUsername(String username);
	 int updatePasswordByUsername(@Param("username")String username,@Param("password")String password);
}
