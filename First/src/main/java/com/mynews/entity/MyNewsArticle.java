package com.mynews.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import lombok.Data;

/**
 * 文章
 * @author 
 *
 */
@Data
public class MyNewsArticle implements Serializable{
	private static final long serialVersionUID = 5549620809787571497L;
	private Integer id;
	private String  title;
	private String img;
	private String content;
	private Integer typeId;
	/*
	 * private Integer commentsCount=0; private Integer readCount=0;
	 */
	private Integer commentsCount;
	private Integer readCount;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUserId;
	private String createdUserName;
	private String articleTypeName;
	private Integer valid=1;
}
