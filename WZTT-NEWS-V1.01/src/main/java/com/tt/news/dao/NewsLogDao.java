package com.tt.news.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.tt.news.entity.NewsLog;

@Mapper
public interface NewsLogDao {
	/**
	 * 基于条件分页查询日志信息
	 * username 查询条件（例如查询那个用户的日志信息）
	 * startIndex 当前页的起始位置
	 * pageSize 当前页的页面大小
	 * return 当前页的日志记录信息
	 * 数据库中每条日志信息封装到一个SysLog对象中
	 *
	 */
	List<NewsLog> findPageObjects(
		@Param("username")String username,//查询条件
		@Param("startIndex")Integer startIndex,//当前页的页面大小
		@Param("pageSize")Integer pageSize		//当前页的页面大小
		);
	/**
	 * 基于条件查询总记录数
	 * @param username 查询条件(例如查询哪个用户的日志信息)
	 * @return 总记录数(基于这个结果可以计算总页数)
	 * 说明：假如如下方法没有使用注解修饰，在基于名字进行查询
	 * 时候会出现There is no getter for property named
	 * 'username' in 'class java.lang.String'
	 */
	int getRowCount(@Param("username")String username);

	/**执行添加操作*/
	int insertObject(NewsLog entity); 
	
	/**根据id查询记录*/
	List<NewsLog> findObjects();
	
}
