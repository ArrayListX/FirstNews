package com.tt.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.news.entity.NewsArticle;
import com.tt.news.service.NewsArticleService;

@RestController
@RequestMapping("/article/")
public class NewsArticleController {
	@Autowired
	private NewsArticleService newsArticleService;
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
    public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(newsArticleService.findObjectById(id));
    }
	
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String title,Integer pageCurrent){
		PageObject<NewsArticle> pageObject=
				newsArticleService.findPageObjects(title, pageCurrent);
		return new JsonResult(pageObject);
	}
	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer... ids) {
		newsArticleService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}
	
	@ResponseBody
	@RequestMapping("doValidById")
	public JsonResult doValidById(Integer id,Integer valid) {
		newsArticleService.validById(id, valid);
		return new JsonResult("update ok");
	}
	
	
}
