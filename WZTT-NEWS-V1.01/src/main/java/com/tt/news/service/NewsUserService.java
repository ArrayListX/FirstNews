package com.tt.news.service;

import java.util.List;

import com.tt.common.vo.PageObject;
import com.tt.news.entity.NewsUser;

public interface NewsUserService {
	/**
     * 获取当前页的分析信息
     * @param username 用户名
     * @param pageCurrent 当前页页码
     * @return 封装了当前页记录以及页码信息的对象
     */
	 PageObject<NewsUser> findPageObjects(
			 String username,
			 Integer pageCurrent);
	 int roleIdById(Integer roleId,Integer id);
	 List<NewsUser> getUser();
}
