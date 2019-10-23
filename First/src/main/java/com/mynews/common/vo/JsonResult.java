package com.mynews.common.vo;

import java.io.Serializable;



/**
 * VO:
 * 借助此对象封装服务端的影响数据
 * 1)响应的状态码 (1表示正常数据,0表示异常数据)
 * 2)响应消息 (呈现给用户的消息,例如一个弹出框中的数据)
 * 3)响应数据 (要呈现的正常数据,例如日志记录信息)
 * 4)....
 */
public class JsonResult implements Serializable{
	private static final long serialVersionUID = -1704311617311414303L;
	/**状态码*/
    public int state=1;
    /**状态码对应的状态信息*/
    private String message="ok";
    /**正常数据*/
    private Object data;
    public JsonResult() {
	}
    public JsonResult(String message) {
    	this.message=message;
    }
    public JsonResult(int state) {
    	this.state=state;
    }
    public JsonResult(Object data) {
    	this.data=data;
    }
    /**封装异常数据*/
    public JsonResult(Throwable e) {
    	this.state=0;
    	this.message=e.getMessage();
    }
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
}














