package com.lx.springbootstorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lx.springbootstorm.storm.TopologyApp;
import com.lx.springbootstorm.utils.SpringContextHelper;
/**
 * 
* @ClassName: MyStartupRunner
* @Description: TODO(Storm（七）通过代码提交Topology:https://www.jianshu.com/p/8d26f3c7c3c8)
* @author hsj
* @date 2019年5月8日
*
 */
@Component
public class MyStartupRunner implements CommandLineRunner {
	@Autowired
	SpringContextHelper springContextHelper;
	
	@SuppressWarnings("static-access")
	public void run(String... arg0) throws Exception {
		 TopologyApp app = springContextHelper.popBean(TopologyApp.class);
		 app.main(arg0);//new String[]{"hsjtest"}
	}
 
}

