package com.mynews.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsUser implements Serializable{
	private static final long serialVersionUID = 3374499450899797986L;
	private Integer id;
	private String head;
	private String username;
	private String email;
	private String password;
	private String salt;
	private Date birthday;
	private String mobile;
	private Integer articleCount;
	private Integer toUserCount;
	private Integer fanCount;
	private Date createdTime;
	private Date modifiedTime;
	private Integer roleId;
}
