package kkamnyang;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kkamnyang.persistence.RouteMapper;
import kkamnyang.service.EventService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BootArchistoryApplication.class})
public class RepositoryTest {

	@Autowired
	RouteMapper mapper;
	
	@Autowired
	   private DataSource dataSource;
	
	   @Autowired
	   private SqlSessionFactory sqlSessionFactory;
	   
	   
	   @Autowired
	   EventService service;
	
	@Test
	public void test() throws Exception{
		Connection con = dataSource.getConnection();
		System.out.println(con);
//		System.out.println(mapper.list());
	}

	@Test
	public void sessionTest() throws Exception{
		System.out.println(sqlSessionFactory);
	}
	
	@Test
	public void routeTest() throws Exception{
		System.out.println(mapper.listAll());
	}
	
	@Test
	public void eventTest() throws Exception{
		System.out.println(service.list());
	}
}
