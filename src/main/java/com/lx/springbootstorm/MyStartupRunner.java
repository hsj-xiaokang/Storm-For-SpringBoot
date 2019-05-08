package com.lx.springbootstorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lx.springbootstorm.storm.TopologyApp;
import com.lx.springbootstorm.utils.SpringContextHelper;

@Component
public class MyStartupRunner implements CommandLineRunner {
	@Autowired
	SpringContextHelper springContextHelper;
	
	@SuppressWarnings("static-access")
	public void run(String... arg0) throws Exception {
		 TopologyApp app = springContextHelper.popBean(TopologyApp.class);
		 app.main(arg0);
	}
 
}

