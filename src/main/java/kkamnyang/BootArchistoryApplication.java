package kkamnyang;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootArchistoryApplication {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	      sqlSessionFactoryBean.setDataSource(datasource);
	      return sqlSessionFactoryBean.getObject();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(BootArchistoryApplication.class, args);
	}
}
