package com.qtummatrix.test;

import com.qtummatrix.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 开启一个发布消息的线程
 */
public class PublishMessage {
    private Logger logger = LoggerFactory.getLogger(PublishMessage.class);

    /**
     * 发布消息
     *
     * @param channel 频道
     * @param message 信息
     */
    public void sendMessage(final String channel, final String message) {
        Thread thread = new Thread(() -> {
            Long publish = RedisUtils.getJedis().publish(channel, message);
            System.out.println("服务器在:"+channel+" 频道发布消息"+message+" - "+publish);
        });
        System.out.println("发布线程启动:");
        thread.setName("publishThread");
        thread.start();
    }

    public static void main(String[] args) {
        PublishMessage message=new PublishMessage();
        message.sendMessage("channel1","测试消息");
    }
}
