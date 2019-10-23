package com.mynews.controller;

import java.util.Date;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mynews.common.exception.ServiceException;
import com.mynews.common.util.CodeUtils;
import com.mynews.common.util.MailUtils;
import com.mynews.common.vo.JsonResult;
import com.mynews.entity.NewsUser;
import com.mynews.service.NewsUserService;

@Controller
@RequestMapping("/user/")
public class NewsUserController {
	@Autowired
	private NewsUserService newsUserService;
	
    @RequestMapping("doUserById")
  	@ResponseBody
  	public JsonResult doUserIdById(String username,Date birthday,String mobile) {
    	System.out.println(username);
  		return new JsonResult(newsUserService.updateUserById(username,birthday,mobile));
  		
  	}    

	@RequestMapping("doLoginUI")
	@ResponseBody
	public JsonResult doLogin(String username) {
		NewsUser user = newsUserService.findUserByUserName(username);
		if(user==null) {
			return new JsonResult(5);
		}
		return new JsonResult(user);
	}	
	//注册
	@RequestMapping("doRegistUI")
	@ResponseBody
	public JsonResult doRegistUI(NewsUser user) {
		newsUserService.insertObject(user);
		   return new JsonResult("save ok");
	}
	//获取邮箱 
	@RequestMapping("doRegist")
	@ResponseBody
	public JsonResult doRegist(String email) {		
		newsUserService.emailCode(email);
		return new JsonResult("已发送验证码");
	}
	@RequestMapping("code")
	@ResponseBody
	public boolean doCode(String emailcode) {	
		boolean flag = newsUserService.docode(emailcode);
		System.out.println("emailcode"+emailcode);
		return flag;
	}
	
	@RequestMapping("findByUsername")
	@ResponseBody
	public JsonResult doFindByUsername(String username) {
		Map<String, Object> findByUsername = newsUserService.findByUsername(username);
		return new JsonResult(findByUsername);
	}
	@RequestMapping("updatePassword")
	@ResponseBody
	public JsonResult updatePasswordByUsername(String username,String password) {
		newsUserService.updatePasswordByUsername(username, password);
		return new JsonResult("update ok");
	}
}
