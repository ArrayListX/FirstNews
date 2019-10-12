package com.tt.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.news.entity.NewsRole;
import com.tt.news.service.NewsRoleService;

@Controller
@RequestMapping("/role/")
public class NewsRoleController {
	@Autowired
	private NewsRoleService newsRoleService;
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
		PageObject<NewsRole> pageObject = newsRoleService.findPageObjects(name, pageCurrent);
		return new JsonResult(pageObject);
	}
}
