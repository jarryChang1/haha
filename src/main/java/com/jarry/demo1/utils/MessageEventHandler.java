package com.jarry.demo1.utils;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.jarry.demo1.Entry.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-21 14:22
 */
@Component
@Slf4j
public class MessageEventHandler {

    @Autowired
    private SocketIOServer socketIOServer;

    public static ConcurrentMap<String,SocketIOClient> socketIOClientConcurrentMap = new ConcurrentHashMap<>();

    /**
     * 客户端连接时触发
     */
    @OnConnect
    public void onConnect(SocketIOClient socketIOClient){
        String mac = socketIOClient.getHandshakeData().getSingleUrlParam("mac");
        //储存SocketIOClient ,用于发送消息
        socketIOClientConcurrentMap.put(mac,socketIOClient);
        //回发消息
        socketIOClient.sendEvent("message","onConnect Back");
        log.info("客户端："+ socketIOClient.getSessionId() + "已连接，mac = " + mac);
    }

    /**
     * 客户端关闭连接时触发
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client){
        log.info("用户已关闭连接，Id：" + client.getSessionId());
    }

    /**
     * 客户端事件
     */
    @OnEvent(value = "messageevent")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, Message data){
        log.info("发来消息：" + data);
        //回发消息
        client.sendEvent("messageevent","我是服务器发送的消息");
        sendBroadcast();
    }

    /**
     * 广播消息
     */
    public void sendBroadcast(){
        for (SocketIOClient client : socketIOClientConcurrentMap.values()){
            if (client.isChannelOpen()) client.sendEvent("Broadcast","当前时间",System.currentTimeMillis());
        }
    }
}

