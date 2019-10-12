package com.tt.news.entity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class NewsRole implements Serializable{
	private static final long serialVersionUID = 8094272493294939469L;
	private Integer id;
	private String name;
	private String note;//简述
	private Date createdTime;//创建时间
	private Date modifiedTime; //修改时间
	private String createdUser;//创建用户
	private String modifiedUser;//修改用户
}
