package com.mynews.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.common.exception.ServiceException;
import com.common.util.CodeUtils;
import com.common.util.MailUtils;
import com.mynews.dao.NewsUserDao;
import com.mynews.entity.NewsUser;
import com.mynews.service.NewsUserService;
@Service
public class NewsUserServiceImpl implements NewsUserService{
	String emailCode;
	@Autowired
	private NewsUserDao newsUserDao;
//	@Autowired
//	private NewsUser newsUser;
	@Override
	public NewsUser findUserByUserName(String username) {
		if(username=="")
			throw new IllegalArgumentException("用户名不能为空");
		NewsUser user = newsUserDao.findUserByUserName(username);
		return user;
	}
	@Override
	public int updateUserById(String username,Date birthday,String mobile) {
		//1.参数有效性验证
		 if(mobile==null)
			throw new IllegalArgumentException("用户手机号不能为空");
		 if(birthday==null)
		  throw new IllegalArgumentException("用户生日不能为空");
		int rows = newsUserDao.updateUserById(username,birthday,mobile);
		//System.out.println(rows);
		return rows;
}
	@Override
	public int insertObject(NewsUser entity) {
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new ServiceException("密码不能为空");
		if(StringUtils.isEmpty(entity.getEmail()))
			throw new ServiceException("邮箱地址不能为空不能为空");
		//2.将数据写入数据库
//				String salt=UUID.randomUUID().toString();
//				entity.setSalt(salt);
//				//加密(先了解,讲shiro时再说)
//				SimpleHash sHash=
//						new SimpleHash("MD5",//算法
//								entity.getPassword(),//原密码
//								salt,//盐值
//								1);
//				entity.setPassword(sHash.toHex());
		int row = newsUserDao.insertObject(entity);
		return row;
	}
	@Override
	public void emailCode(String email) {
		emailCode = new String((int)((Math.random()*9+1)*100000)+"");
		String code = CodeUtils.generateUniqueCode();	
		new MailUtils(email, code).run(emailCode);
	}
	
	@Override
	public boolean docode(String emailcode) {
		boolean flag=false;
		if(!emailCode.equals(emailcode)) {
			return flag;
		}
		return flag=true;
	}
	
	//根据username查找全部的值
	@Override
	public Map<String, Object> findByUsername(String username) {
		if(username==null)
			throw new ServiceException("名字不能为空");
		NewsUser entity = newsUserDao.findByUsername(username);
		Map<String,Object> map=new HashMap<>();
		map.put("mobile", entity.getMobile());
		map.put("birthday", entity.getBirthday());
		return map;
	}
	@Override
	public int updatePasswordByUsername(String username, String password) {
		if(StringUtils.isEmpty(password))
			throw new ServiceException("密码不能为空");
		int row = newsUserDao.updatePasswordByUsername(username, password);
		return row;
	}
	
	
	
}
