package com.qtummatrix.test;

import com.qtummatrix.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *订阅频道，取消频道等动作类
 */
public class SubScribeMessage {
    private Logger logger = LoggerFactory.getLogger(SubScribeMessage.class);

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    /**
     * 订阅频道
     *
     * @param channel     频道
     * @param roomSubListerner
     */
    public void subscribeChannel(final String channel, final ChatSubscribe roomSubListerner) {

        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                RedisUtils.getJedis().subscribe(roomSubListerner, channel);
            }
        });
    }

    public static void main(String[] args) {
        SubScribeMessage message=new SubScribeMessage();
        message.subscribeChannel("channel1",new ChatSubscribe());
    }
}
