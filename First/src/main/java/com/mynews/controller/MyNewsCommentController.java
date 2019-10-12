package com.mynews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.common.vo.JsonResult;
import com.common.vo.PageObject;
import com.mynews.entity.MyNewsComment;
import com.mynews.service.MyNewsCommentService;

@RestController
@RequestMapping("article/mycomment/")
public class MyNewsCommentController {

	@Autowired
	private MyNewsCommentService myNewsCommentService;
	
	@RequestMapping("doDeleteObjectById")
	public JsonResult doDeleteObjectById(Integer id,String title,String createdUser) {		
		myNewsCommentService.deleteObjectById(id,title,createdUser);
		return new JsonResult("删除成功");
	}


	@RequestMapping("doListComments")
	public JsonResult doListComments(String title) {
		List<MyNewsComment> comments = myNewsCommentService.listComments(title);
		return new JsonResult(comments);
	}
	/**===========================================添加评论====================================================*/
	@RequestMapping("doSaveObject")
	 public JsonResult doSaveObject(String content,String articleTitle,String createdUser) {//传入 页面获取的文章标题和评论内容
		// System.out.println(content+","+articleTitle);
		 int row = myNewsCommentService.saveObject(content, articleTitle,createdUser);
		 System.out.println(row);
		 return new JsonResult("Save OK");
	 }

	
}