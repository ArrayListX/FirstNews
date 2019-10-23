package com.mynews.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mynews.common.vo.JsonResult;
import com.mynews.common.vo.PageObject;
import com.mynews.entity.MyNewsArticle;
import com.mynews.service.MyNewsArticleService;

@Controller
@RequestMapping("/article/")
public class MyNewsArticleController {
	@Autowired
	private MyNewsArticleService myNewsArticleService;
	
	@RequestMapping("doFindObjectsById")
	//@ResponseBody
    public String doFindObjectsById(HttpServletResponse res,Integer id) {
		Cookie cookie=new Cookie("articleId",String.valueOf(id));
		res.addCookie(cookie);
		return "ch/article_view";
    }
	
	@RequestMapping("doUserArticle")
	//@ResponseBody
    public String doUserArticle() {
		return "ch/otherUser";
    }
	
	@RequestMapping("doShowId")
	@ResponseBody
	public JsonResult doShowId(Integer id) {
		myNewsArticleService.addReadCount(id);
		return new JsonResult(myNewsArticleService.findObjectsById(id));
	}
	@RequestMapping("doFindObject")
	@ResponseBody
	public JsonResult doFindObjectsId(String typeName) {
		return new JsonResult(myNewsArticleService.findObject(typeName));
	}
	
	
}
