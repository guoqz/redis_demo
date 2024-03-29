package com.qtummatrix.test;

import com.qtummatrix.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.Resource;

/**
 * 用于处理订阅相关事件
 */
public class ChatSubscribe extends JedisPubSub {
    private Logger logger = LoggerFactory.getLogger(ChatSubscribe.class);

    // 取得订阅的消息后的处理
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("订阅成功,接收到的消息为:频道-"+channel+",消息-"+message);
    }

    // 取得按表达式的方式订阅的消息后的处理
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("-----取得按表达式的方式订阅的消息后的处理-----");
        System.out.println(pattern + "=" + channel + "=" + message);
    }

    // 初始化按表达式的方式订阅时候的处理
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("-----初始化按表达式的方式订阅时候的处理-----");
        System.out.println(pattern + "=" + subscribedChannels);
    }

    // 取消按表达式的方式订阅时候的处理
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("-----取消按表达式的方式订阅时候的处理-----");
        System.out.println(pattern + "=" + subscribedChannels);
    }

    @Override
    public void onPong(String pattern) {
        super.onPong(pattern);
    }

    // 初始化订阅时候的处理
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("初始化订阅信息:频道-"+channel+",订阅频道-"+subscribedChannels);
    }

    // 取消订阅时候的处理
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("已取消订阅频道"+channel);
    }
}
