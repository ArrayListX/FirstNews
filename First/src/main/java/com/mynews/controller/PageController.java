package com.mynews.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * PageController负责呈现项目中所有的页面
 * @author Administrator
 */
@Controller
@RequestMapping("/")
public class PageController {
	  /**返回首页页面*/
	  @RequestMapping("doIndexUI")
	  public String doIndexUI() {
		  return "start";
	  }
	  /**返回分页对应的页面*/
	  @RequestMapping("doPageUI")
	  public String doPageUI() {
		  return "common/page";
	  }
	  @RequestMapping("doLoginUI")
	  public String doLoginUI(){
	  		return "login";
	  }
	  @RequestMapping("doUserUI")
	  public String doUserUI(){
		  return "userSet";
	  		
	  }
	  @RequestMapping("doRegistUI")
	  public String doRegistUI() {
		  return "regist";
	  }
	  /**返回某个模块的UI页面(rest架构编码风格)*/
	  @RequestMapping("{module}/{ui}")
	  public String doModuleUI(@PathVariable String ui) {
		  return "ch/"+ui;
	  }
}



