package com.jarry.demo1.utils.httpUtils;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.httpUtils
 * @Author: Jarry.Chang
 * @CreateTime: 2020-05-19 14:21
 */
public class DefaultAuthenticator extends Authenticator {
    private static Map<String, PasswordAuthentication> hostAuthMap = new ConcurrentHashMap<String, PasswordAuthentication>();

    public static void addAuthentication(String host, int port, String authName, String authPasswd) {
        String key = host + ":" + port;
        hostAuthMap.put(key, new PasswordAuthentication(authName, authPasswd.toCharArray()));
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String key = this.getRequestingHost() + ":" + this.getRequestingPort();
        return hostAuthMap.get(key);
    }



}
