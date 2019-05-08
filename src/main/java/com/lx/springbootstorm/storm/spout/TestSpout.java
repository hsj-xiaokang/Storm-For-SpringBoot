package com.lx.springbootstorm.storm.spout;


import com.lx.springbootstorm.entity.User;
import com.lx.springbootstorm.service.UserService;
import com.lx.springbootstorm.storm.TopologyApp;
import com.lx.springbootstorm.utils.SpringContextHelper;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestSpout extends BaseRichSpout {
    private final static Logger logger = LoggerFactory.getLogger(TestSpout.class);
    
    List<Map<String, Object>> mapType;
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private SpoutOutputCollector collector;
    
	@Autowired
	SpringContextHelper springContextHelper;

    @Override
    public void nextTuple() {
        try {
//            Thread.sleep(1000);
            User user = userService.selectByPrimaryKey(1);
            collector.emit(new Values(user));
            logger.info("================Spout发送数据======================="+"Time："+new Date() +"--"+"userId:"+user.getUserId()+"--"+"username:"+user.getUserName());
            User user2 = userService.selectByPrimaryKey(2);
            collector.emit(new Values(user2));
            //*collector.emit(new Values("123456"));
            logger.info("================Spout发送数据======================="+"Time："+new Date() +"--"+"userId:"+user2.getUserId()+"--"+"username:"+user2.getUserName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @SuppressWarnings("static-access")
	@Override
    public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
        try {
            userService= springContextHelper.popBean(UserService.class);
            collector = arg2;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer arg0) {

        arg0.declare(new Fields("str"));
    }
}