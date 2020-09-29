package com.jarry.demo1.utils;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.jarry.demo1.Entry.Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.security.auth.message.MessageInfo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static ConcurrentMap<String, ClientInfo> socketIOClientConcurrentMap = new ConcurrentHashMap<>();

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private final SocketIOServer server;

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        this.server = server;
    }

    /**
     * 客户端连接时触发
     */
    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {
        String clientId = socketIOClient.getHandshakeData().getSingleUrlParam("clientid");
        UUID session = socketIOClient.getSessionId();
        ClientInfo si = socketIOClientConcurrentMap.get(clientId);

        if (si == null) {
            si = new ClientInfo();
            si.setOnline(true);
            log.info("socket 建立新连接、sessionId:" + session + "、clientId:" + clientId + "、当前连接数：" + onlineCount.incrementAndGet());
        }
        si.setMostSignificantBits(session.getMostSignificantBits());
        si.setLeastSignificantBits(session.getLeastSignificantBits());
        si.setLastConnectedTime(new Date());

        socketIOClientConcurrentMap.put(clientId, si);
//        String mac = socketIOClient.getHandshakeData().getSingleUrlParam("mac");
//        //储存SocketIOClient ,用于发送消息
//        socketIOClientConcurrentMap.put(mac,socketIOClient);
//        //回发消息
//        socketIOClient.sendEvent("message","onConnect Back");
//        log.info("客户端："+ socketIOClient.getSessionId() + "已连接，mac = " + mac);
    }

    @Data
    private class ClientInfo {
        private String clientId;
        private boolean isOnline;
        private long mostSignificantBits;
        private long leastSignificantBits;
        private Date lastConnectedTime;
        private int userType;
        // get/set方法 ....
    }

    /**
     * 管理员事件、管理员登录即触发
     */
    @OnEvent(value = "manager_login")
    public void onMLEvent(SocketIOClient client, AckRequest ackRequest, Message data) {

        //将自己的sessionId、clinetInfo打上管理员标记；

        //返回给客户端在线用户的map信息。
    }

    /**
     * 客户端关闭连接时触发
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
        socketIOClientConcurrentMap.remove(clientId);

        log.info("socket 断开连接、sessionId:" + client.getSessionId() + "、clientId:" + clientId + "、当前连接数：" + onlineCount.decrementAndGet());
    }

    /**
     * 客户端事件
     */
    @OnEvent(value = "messageevent")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, Message data) {
        log.info("发来消息：" + data);
        //回发消息
        client.sendEvent("messageevent", "我是服务器发送的消息");
//        sendBroadcast();
    }

    /**
     * 消息接收入口，当接收到消息后，查找目标客户端，给目标和自己各发一次消息
     */
    @OnEvent(value = "message_event")
    public void onMEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = simpleDateFormat.format(new Date());
        //聊天记录（人员）持久化=====调用
        //聊天记录缓存策略（登录即续费toMaxlivetime）----ZSet
        String targetClientId = data.getTargetClientId();
        ClientInfo clientInfo = socketIOClientConcurrentMap.get(targetClientId);
        if (clientInfo != null && clientInfo.isOnline()) {
            UUID target = new UUID(clientInfo.getMostSignificantBits(), clientInfo.getLeastSignificantBits());
            MessageInfo sendData = new MessageInfo();
            sendData.setSourceClientId(data.getSourceClientId());
            sendData.setTargetClientId(data.getTargetClientId());
            sendData.setMsg(data.getMsg());
            client.sendEvent("message_event", sendData);

            server.getClient(target).sendEvent("message_event", sendData);
        }
    }

//    /**
//     * 广播消息
//     */
//    public void sendBroadcast(){
//        for (SocketIOClient client : socketIOClientConcurrentMap.values()){
//            if (client.isChannelOpen()) client.sendEvent("Broadcast","当前时间",System.currentTimeMillis());
//        }
//    }

    @Data
    private static class MessageInfo implements Serializable {
        //源客户端id
        private String sourceClientId;
        //目标客户端id
        private String targetClientId;
        //消息内容
        private String msg;
        // get/set方法 ....
    }
}

