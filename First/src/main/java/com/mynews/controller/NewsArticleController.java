package com.mynews.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.common.vo.JsonResult;
import com.common.vo.PageObject;
import com.mynews.entity.NewsArticle;
import com.mynews.entity.NewsUser;
import com.mynews.service.NewsArticleService;



@RestController
@RequestMapping("/article/")
public class NewsArticleController {
	@Autowired
	private NewsArticleService newsArticleService;

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		NewsArticle p = newsArticleService.findObjectById(id);
		return new JsonResult(p);
	}

	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(NewsArticle entity) {
		newsArticleService.saveObject(entity);
		return new JsonResult("保存成功");
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

	@RequestMapping("doUpdateById")
	@ResponseBody
	public JsonResult doUpdateById(Integer id,Integer typeId,String title,String content) {
		System.out.println(id);
		newsArticleService.updateArticleById(id,typeId,title,content);
		return new JsonResult("修改成功!");

	}
	@RequestMapping("uploadimg")
	@ResponseBody
	public JsonResult uploadImages1(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){

		String uploadPath = "D:/images/";
		String fileName = uploadFile(uploadPath,file);
		System.out.println(fileName);
		JsonResult jsonResult=new JsonResult();
		jsonResult.setData(fileName);
		return jsonResult;
	}
	private String uploadFile(String uploadPath,MultipartFile file){
		InputStream inputStream = null;
		OutputStream os = null;
		String path = null;
		String fileName = new Date().getTime()+"_"+file.getOriginalFilename();
		try{
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流保存到本地文件
			File tempFile = new File(uploadPath);
			if (!tempFile.exists()) {
				tempFile.mkdirs();
			}
			inputStream = file.getInputStream();
			path = tempFile.getPath() + File.separator + fileName;
			os = new FileOutputStream(path);
			// 开始读取
			while ((len = inputStream.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			// 完毕，关闭所有链接
			try {
				os.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return  fileName;
	}
}


