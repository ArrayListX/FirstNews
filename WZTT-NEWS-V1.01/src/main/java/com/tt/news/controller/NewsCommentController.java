package com.tt.news.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tt.common.util.PoiUtils;
import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.news.entity.NewsComment;
import com.tt.news.service.NewsCommentService;


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

	/**==================导出Excel=================================*/
	/**
	 * 导出报表
	 * @return
	 */

	@RequestMapping("CommentExcel")
	public void CommentExcel(HttpServletResponse resp) throws Exception {
		//获取数据
		List<NewsComment> list = newsCommentService.listObjects();

		//标题
		String title = "用户评论表";

		//excel标题
		String[] rowsName = {"序号","评论用户","评论时间","评论对应文章","评论内容","删除评论时间","删除用户"};

		List<Object[]>  dataList = new ArrayList<Object[]>();
		//存放评论信息的数组
		Object[] content = null;

		int rowlength = rowsName.length;
		int pricesize = list.size();

		for (int i = 0; i<pricesize;i++){
			NewsComment comment =list.get(i);
			content = new Object[rowlength]; 
			content[0] = i;
			content[1] = comment.getCreatedUser();
			content[2] = comment.getCreatedTime();
			content[3] = comment.getArticleId();
			content[4] = comment.getContent();
			content[5] = comment.getDeleteTime();
			content[6] = comment.getDeleteUser();
			dataList.add(content);
		}
		PoiUtils poi = new PoiUtils(title, rowsName, dataList, resp);
		poi.exportData();
	}
	
	
}