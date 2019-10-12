package com.tt.news.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class NewsComment implements Serializable{

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 3064977639670807340L;

	//评论id
	private Integer id;
	//评论用户（显示用户名称）
	private String createdUser;
	//评论时间（yyyy-MM-dd）
	private Date createdTime;
	//评论对应文章id
	private Integer articleId;
	//评论内容
	private String content;
	//删除评论时间（yyyy-MM-dd）
	private Date deleteTime;
	//删除用户（显示用户名称）
	private String deleteUser;
	//操作（删除操作）
	private Integer valid;



}
