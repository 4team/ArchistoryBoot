package kkamnyang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kkamnyang.repository.RouteRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class RepositoryTest {

	private RouteRepository rrep;
	
	@Autowired
	public void setRouteRepository(RouteRepository route){
		this.rrep = route;
	}
	
	@Test
	public void test() {
		
		boolean vo = rrep.exists(56);
		System.out.println(vo);
		
	}

}
