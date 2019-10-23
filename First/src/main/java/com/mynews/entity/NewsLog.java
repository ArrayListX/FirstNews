package com.mynews.entity;

import java.io.Serializable;
import java.util.Date;

import com.mynews.entity.NewsLog;

import lombok.Data;

@Data
public class NewsLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3137973644230162075L;
	private Integer id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long timebig;
	//IP地址
	private String ip;
	//创建时间
	private Date createdTimedate;
	public NewsLog setId(Integer id) {
		this.id=id;
		return this;
	}
	
	public NewsLog setIp(String ip) {
		this.ip=ip;
		return this;
	}
	
	public NewsLog() {
		super();
	}
	
	public NewsLog(Integer id, String username, String operation, String method, String params, Long timebig,
			String ip, Date createdTimedate) {
		super();
		this.id = id;
		this.username = username;
		this.operation = operation;
		this.method = method;
		this.params = params;
		this.timebig = timebig;
		this.ip = ip;
		this.createdTimedate = createdTimedate;
	}
}
