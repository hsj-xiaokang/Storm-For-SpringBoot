package com.lx.springbootstorm.storm.bolt;


import com.lx.springbootstorm.entity.User;
import com.lx.springbootstorm.service.UserService;
import com.lx.springbootstorm.utils.GetSpringBean;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public class InsertBolt extends BaseRichBolt{

    /**
     *
     */
    private static final long serialVersionUID = 6542256546124282695L;


    private static final Logger logger = LoggerFactory.getLogger(InsertBolt.class);


    private UserService userService;


    @SuppressWarnings("rawtypes")
    @Override
    public void prepare(Map map, TopologyContext arg1, OutputCollector collector) {
       userService= GetSpringBean.getBean(UserService.class);
    }


    @Override
    public void execute(Tuple tuple) {
        User user =  (User) tuple.getValueByField("str");
        //User user = userService.selectByPrimaryKey(1);
        //String a = (String) tuple.getValueByField("str");
        try{
            System.out.println("Time："+new Date() +"--"+"userId:"+user.getUserId()+"--"+"username:"+user.getUserName());
           // System.out.println(user.getUserId());

        }catch(Exception e){
            logger.error("Bolt的数据处理失败!数据:{}",user.getUserId(),e);
        }
    }


    /**
     * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
     * Storm在终止一个bolt之前会调用这个方法。
     */
    @Override
    public void cleanup() {
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        arg0.declare(new Fields("str"));
    }


}