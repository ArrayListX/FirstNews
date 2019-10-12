package com.tt.news.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class NewsUser implements Serializable{
	private static final long serialVersionUID = -2706355608835090724L;
	private Integer id;
	/**头像路径*/
	private String head;
	/**用户名*/
	private String username;
	/**邮箱*/
	private String email;
	/**密码*/
	private String password;
	/**盐 密码加密时前缀，使加密后的值不同 */
	private String salt;
	/**生日*/
	private Date birthday;
	/**手机号*/
	private String mobile;
	/**已发表的文章数*/
	private Integer articleCount = 0;
	/**关注数*/
	private Integer toUserCount = 0;
	/**粉丝数*/
	private Integer fanCount = 0;
	/**创造时间*/
	private Date createdTime;
	/**修改时间*/
	private Date modifiedTime;
	/**用户类别： 1：正常 2：违规 3：禁用 默认值 ：1 */
	private Integer roleId = 1;
}
