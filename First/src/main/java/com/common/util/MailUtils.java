package com.common.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;
public class MailUtils{
	private String email;
	private String code; // 激活码
	public MailUtils(String email, String code) {
		this.email = email;
		this.code = code;
	}
	public MailUtils() {
	}
	
	public void run(String emailcode) {
	  // 配置
	  Properties prop=new Properties();
	  // 设置邮件服务器主机名，这里是163
	  prop.put("mail.host","smtp.163.com" );
	  // 发送邮件协议名称
	  prop.put("mail.transport.protocol", "smtp");
	  // 是否认证
	  prop.put("mail.smtp.auth", true);

	  try {
	    // SSL加密
	    MailSSLSocketFactory sf = null;
	    sf = new MailSSLSocketFactory();
	    // 设置信任所有的主机
	    sf.setTrustAllHosts(true);
	    prop.put("mail.smtp.ssl.enable", "true");
	    prop.put("mail.smtp.ssl.socketFactory", sf);

	    // 创建会话对象
	    Session session = Session.getInstance(prop, new Authenticator() {
	      // 认证信息，需要提供"用户账号","授权码"
	      public PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication("t_jxcui@163.com", "a123456");
	      }
	    });
	    // 是否打印出debug信息
	    session.setDebug(true);

	    // 创建邮件
	    Message message = new MimeMessage(session);
	    // 邮件发送者
	    message.setFrom(new InternetAddress("t_jxcui@163.com"));
	    // 邮件接受者
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	    // 邮件主题    
	    message.setSubject("验证码");
	    message.setText("验证码是："+emailcode+"。此邮件无需回复！！！");
	    Transport.send(message);
	    // 邮件发送
	   
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	}
}