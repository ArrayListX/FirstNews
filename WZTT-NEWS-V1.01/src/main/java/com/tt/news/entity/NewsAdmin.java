package com.tt.news.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class NewsAdmin implements Serializable{
	private static final long serialVersionUID = -8519322764203385612L;
	private Integer id;
	private String username;
	private String password;
	private String salt;//盐值
}
