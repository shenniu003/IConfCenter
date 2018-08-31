package com.ct.tjedis;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by Administrator on 2018/8/30.
 */
public class JedisSubscriber extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {

        System.out.println(String.format("时间：%s,频道：%s,收到消息：%s",
                System.currentTimeMillis(),
                channel,
                message));

        super.onMessage(channel, message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("时间：%s,订阅频道：%s", System.currentTimeMillis(), channel));
        super.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("时间：%s,取消订阅：%s", System.currentTimeMillis(), channel));
        super.onUnsubscribe(channel, subscribedChannels);
    }



}
