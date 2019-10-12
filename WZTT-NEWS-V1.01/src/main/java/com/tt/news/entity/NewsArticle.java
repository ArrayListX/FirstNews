package com.tt.news.entity;

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
	private Integer commentsCount;
	private Integer readCount;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUserId;
	private Integer valid=1;
}
