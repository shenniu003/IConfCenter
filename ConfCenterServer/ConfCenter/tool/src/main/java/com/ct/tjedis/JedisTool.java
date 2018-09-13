package com.ct.tjedis;

import com.google.gson.Gson;
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

    private Gson gson = new Gson();

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

    /**
     * @param key
     * @param val
     * @param time 单位：秒
     * @return
     */
    public boolean set(String key, String val, long time) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (time <= 0) {
                return jedis.set(key, val).equalsIgnoreCase("OK");
            } else {
                //NX:不存在key创建
                //EX:秒
                return jedis.set(key, val, "NX", "EX", time).equalsIgnoreCase("OK");
            }
        } catch (Exception ex) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public <T> boolean set(String key, T val, long time) {
        return set(key, gson.toJson(val), time);
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception ex) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return "";
    }

    public <T> T get(String key, Class<T> tClass) {
        String val = this.get(key);
        if (val == null || val.isEmpty()) {
            return null;
        }
        return gson.fromJson(val, tClass);
    }
}
