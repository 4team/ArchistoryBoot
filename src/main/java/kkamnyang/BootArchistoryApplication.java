package kkamnyang;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@MapperScan(value={"kkamnyang.persistence"})
public class BootArchistoryApplication{


	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	      sqlSessionFactoryBean.setDataSource(datasource);
	      
	      Resource[] arrResource = new PathMatchingResourcePatternResolver()
	              .getResources("classpath:mappers/*Mapper.xml");
	        sqlSessionFactoryBean.setMapperLocations(arrResource);
	      
	      return sqlSessionFactoryBean.getObject();
	}

    
	public static void main(String[] args) {
		SpringApplication.run(BootArchistoryApplication.class, args);
	}
	
}
