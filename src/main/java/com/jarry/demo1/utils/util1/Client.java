package com.jarry.demo1.utils.util1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.utils.util1
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-25 15:55
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动......");

        Socket socket = new Socket("127.0.0.1",43438);

        OutputStream os = socket.getOutputStream();

        Scanner sc =new Scanner(System.in);
        String message = sc.nextLine();
        os.write(message.getBytes("utf-8"));
        os.flush();

        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();

        byte[] bytes = new byte[1024];
        int len = 0;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1){
           sb.append(new String(bytes,0,len,"utf-8"));
        }
        System.out.println(sb.toString());

        os.close();
        inputStream.close();
        socket.close();
    }
}
