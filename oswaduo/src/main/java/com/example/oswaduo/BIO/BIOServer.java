package com.example.oswaduo.BIO;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.example.oswaduo.BIO
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-27 10:42
 */
public class BIOServer {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("Server started");

        while (true){

            final Socket socket = serverSocket.accept();
            System.out.println("linked a client ");
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }
    public static void handler(Socket socket)throws IOException {
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            //不加循环
            int read = inputStream.read(bytes);
            if (read != -1) System.out.println(new String(bytes,0,read));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            socket.close();
        }
    }
}
