package com.mynews;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mynews.dao.NewsUserDao;
import com.mynews.entity.NewsUser;
import com.mynews.service.NewsUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstApplicationTests {

	@Autowired
	NewsUserDao newsUserDao;
	@Autowired
	NewsUserService newsUserService;
	@Test
	public void contextLoads() {
	}

//	@Test
//	public void updateObjects() {
//		NewsUser findByUsername = newsUserDao.findByUsername("cyx");
//		System.out.println(findByUsername);
//	}
	

}
