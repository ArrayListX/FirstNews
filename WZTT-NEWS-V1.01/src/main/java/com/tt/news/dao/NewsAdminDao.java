package com.tt.news.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tt.news.entity.NewsAdmin;

@Mapper
public interface NewsAdminDao {
	@Select("select * from news_admin where username=#{username}")
	NewsAdmin findAdminByUserName(String username);
}
