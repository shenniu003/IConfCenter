package com.ct.tjedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.function.Consumer;

/**
 * Created by Administrator on 2018/8/31.
 */
@Component
public class JedisTool {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 发布消息
     *
     * @param channel
     * @param message
     */
    public void publish(String channel, String message) {
        new Thread(() -> {
            Jedis jedis = jedisPool.getResource();
            jedis.publish(channel, message);
        }).start();
    }

    /**
     * 订阅消息
     *
     * @param channel
     */
    public void subscribe(String channel, Consumer consumer) {
        new Thread(() -> {
            JedisSubscriber jedisSubscriber = new JedisSubscriber() {
                @Override
                public void onMessage(String channel, String message) {
                    consumer.accept(message);
                }
            };
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                jedis.subscribe(jedisSubscriber, channel);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }).start();

    }
}
