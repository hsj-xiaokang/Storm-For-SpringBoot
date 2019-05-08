package com.lx.springbootstorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootStormApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootStormApplication.class, args);

        /*ConfigurableApplicationContext context = SpringApplication.run(SpringbootStormApplication.class, args);
        GetSpringBean springBean=new GetSpringBean();
		    springBean.setApplicationContext(context);
		    TopologyApp app = context.getBean(TopologyApp.class);
        try {
            app.main(args);
        } catch (InvalidTopologyException e) {
            e.printStackTrace();
        } catch (AuthorizationException e) {
            e.printStackTrace();
        } catch (AlreadyAliveException e) {
            e.printStackTrace();
        }*/

    }

}
