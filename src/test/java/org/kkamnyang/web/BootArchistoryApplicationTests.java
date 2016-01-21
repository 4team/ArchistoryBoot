package org.kkamnyang.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import kkamnyang.BootArchistoryApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootArchistoryApplication.class)
@WebAppConfiguration
public class BootArchistoryApplicationTests {

	@Test
	public void contextLoads() {
	}

}
