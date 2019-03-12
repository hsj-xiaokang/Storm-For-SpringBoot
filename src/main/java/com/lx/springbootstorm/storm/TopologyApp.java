package com.lx.springbootstorm.storm;


import com.lx.springbootstorm.entity.User;
import com.lx.springbootstorm.storm.bolt.InsertBolt;
import com.lx.springbootstorm.storm.spout.TestSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;

@Component
public class TopologyApp {
    private final static Logger logger = LoggerFactory.getLogger(TopologyApp.class);

    public static  void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("getCaseSpout", new TestSpout(), 1);
        builder.setBolt(" getCountBolt", new InsertBolt(), 1).shuffleGrouping("getCaseSpout");
        Config config = new Config();
        // 注释掉后，但Studnet没实现java序列化，则会报错。有两种方法，一种注册该类，一种实现java序列化。
        config.registerSerialization(User.class);
        // 这里如果注释掉，则会使用java序列化方式，如果我们取消掉禁止使用java序列化方法，则会提示注册LinkedList类报错。
        config.registerSerialization(LinkedList.class);
        // 禁止使用java语言自己的序列化
        // Config.setFallBackOnJavaSerialization(conf, false);
        config.setDebug(false);
        config.put(Config.NIMBUS_HOST, "192.168.3.154"); // 配置nimbus连接主机地址，比如：192.168.10.1
        config.put(Config.NIMBUS_THRIFT_PORT, 6627);// 配置nimbus连接端口，默认
        // 6627
        config.put(Config.STORM_ZOOKEEPER_SERVERS, Arrays.asList("127.0.0.1")); // 配置zookeeper连接主机地址，可以使用集合存放多个
        config.put(Config.STORM_ZOOKEEPER_PORT, 2181); // 配置zookeeper连接端口，默认2181
        /*
         * 初级工程师本地模式和准生产测试时，topology的work的数量都为1，
         * 导致对象在bolt和bolt节点传输时并没有走序列化方式，结果测试一切正常， 但是上生产后，因为work数量是10个，
         * 立马在后一个bolt中报大量的空指针异常， 造成很严重的生产问题。
         */
        config.setNumWorkers(1);
        if (args != null && args.length > 0) {
            // 远程模式
            StormSubmitter.submitTopology(args[0], config, builder.createTopology());
        } else {
            // 本地模式
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("simple", config, builder.createTopology());
        }

        // StormSubmitter.submitTopology(args[0], config.properties,
        // builder.createTopology());

    }

}
