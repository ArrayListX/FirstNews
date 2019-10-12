package com.mynews.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章
 * @author 
 *
 */
@Data
public class NewsArticle implements Serializable{
	private static final long serialVersionUID = 5549620809787571497L;
	private Integer id;
	private String  title;
	private String content;
	private Integer typeId;
	private Integer commentsCount=0;
	private Integer readCount=0;
	private Date createdTime;
	private Date modifiedTime;
	private Integer createdUserId;
	private String username;
	private Integer valid=1;
	private String img;
}
