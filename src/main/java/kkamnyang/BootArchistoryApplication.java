package kkamnyang;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@MapperScan(value={"kkamnyang.persistence"})
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
