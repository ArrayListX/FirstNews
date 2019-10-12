package com.tt.news.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tt.common.util.PoiUtils;
import com.tt.common.vo.JsonResult;
import com.tt.news.entity.NewsUser;
import com.tt.news.service.NewsUserService;



@RestController
@RequestMapping("/user/")
public class NewsUserController {
     @Autowired
	 private NewsUserService newsUserService;
     @RequestMapping("doFindPageObjects")
	 public JsonResult doFindPageObjects(
			 String username,Integer pageCurrent) {
		 return new JsonResult(newsUserService.findPageObjects(username, pageCurrent));
	 }
    @RequestMapping("doRoleIdById")
 	@ResponseBody
 	public JsonResult doRoleIdById(	
 			Integer roleId,
 			Integer id) {
 		newsUserService.roleIdById(roleId, id);
 				return new JsonResult("update ok");
 		
 	}
 	@RequestMapping("/userExcel")
 	@ResponseBody
 	public void priceExcel(HttpServletResponse resp){
 		//查询价格表
 		List<NewsUser> list = newsUserService.getUser();
 		String title = "用户信息表";
 		String[] rowsName = new String[]{"序号","用户","邮箱","生日","手机号","文章量","关注数","粉丝数","创建时间","修改时间"};
 		List<Object[]>  dataList = new ArrayList<Object[]>();  
        Object[] objs = null;
        int rowlength = rowsName.length;
        int pricesize = list.size();
        for (int i = 0; i<pricesize;i++){
         	NewsUser user =list.get(i);
         	objs = new Object[rowlength]; 
         	objs[0] = i;
         	objs[1] = user.getUsername();
         	objs[2] = user.getEmail();
         	objs[3] = user.getBirthday();
         	objs[4] = user.getMobile();
         	objs[5] = user.getArticleCount();
         	objs[6] = user.getToUserCount();
         	objs[7] = user.getFanCount();
         	objs[8] = user.getCreatedTime();
         	objs[9] = user.getModifiedTime();
         	dataList.add(objs);
 		}
         PoiUtils poi = new PoiUtils(title, rowsName, dataList, resp);
         poi.exportData();
 	}
}
