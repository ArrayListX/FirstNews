package com.mynews.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mynews.common.vo.JsonResult;
import com.mynews.common.vo.PageObject;
import com.mynews.entity.NewsComment;
import com.mynews.service.NewsCommentService;



@RestControllerAdvice
@RequestMapping("/comment/")
public class NewsCommentController {

	@Autowired
	private NewsCommentService newsCommentService;

	@RequestMapping("doDeleteObjectById")
	public JsonResult doDeleteObjectById(Integer id) {
		newsCommentService.deleteObjectById(id);
		return new JsonResult("删除成功");
	}

	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
			String username,Integer pageCurrent) {
		PageObject<NewsComment> pageObject=newsCommentService.findPageObjects(username, pageCurrent);
		return new JsonResult(pageObject);
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
    public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(newsCommentService.findObjectById(id));
    }
	
	
}