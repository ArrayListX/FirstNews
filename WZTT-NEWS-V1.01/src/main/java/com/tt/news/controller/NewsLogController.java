package com.tt.news.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tt.common.util.PoiUtils;
import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.news.entity.NewsLog;
import com.tt.news.service.NewsLogService;


@RestController
@RequestMapping("/log/")
public class NewsLogController {
	@Autowired
	private NewsLogService newsLogService;
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String username,Integer pageCurrent){
	 PageObject<NewsLog> pageObject=
		newsLogService.findPageObjects(username,pageCurrent);
	 return new JsonResult(pageObject);
	}
	@RequestMapping("reportExcel")
	public void priceExcel(HttpServletResponse resp){
		//查询日志表
		List<NewsLog> list = newsLogService.findObjects();
		String title = "日志记录表";
		String[] rowsName = new String[]{"序号","用户名","用户操作","请求方法","请求参数","执行时长","IP地址","创建时间"};
		List<Object[]>  dataList = new ArrayList<Object[]>();  
        Object[] objs = null;
        int rowlength = rowsName.length;
        int pricesize = list.size();
        for (int i = 0; i<pricesize;i++){
        	NewsLog pojo =list.get(i);
        	objs = new Object[rowlength]; 
        	objs[0] = i;
        	objs[1] = pojo.getUsername();
        	objs[2] = pojo.getOperation();
        	objs[3] = pojo.getMethod();
        	objs[4] = pojo.getParams();
            objs[5] =pojo.getTimebig();
            objs[6] =pojo.getIp();
            objs[7] = pojo.getCreatedTimedate();        
        	dataList.add(objs);
		}
        PoiUtils poi = new PoiUtils(title, rowsName, dataList, resp);
        poi.exportData();
	}
}
