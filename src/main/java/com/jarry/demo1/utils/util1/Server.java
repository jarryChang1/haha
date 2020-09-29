package com.jarry.demo1.utils.util1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-25 15:49
 */
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动.....");

        int port = 43438;
        ServerSocket serverSocket = new ServerSocket(port);

        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, "utf-8"));

        }
        System.out.println(sb.toString());
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
