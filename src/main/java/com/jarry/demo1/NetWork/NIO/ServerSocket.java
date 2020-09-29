package com.jarry.demo1.NetWork.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.NetWork.NIO
 * @Author: Jarry.Chang
 * https://segmentfault.com/a/1190000006824196
 * @CreateTime: 2020-08-31 17:22
 */
public class ServerSocket {
    public ServerSocket() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();
        serverSocketChannel.configureBlocking(false);
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_READ);//register时可以添加对象
        /**获取channel、selector*/
        Channel channel = key.channel();
        Selector selector1 = key.selector();
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                //doSomething

                break;
            }
        }
        serverSocketChannel.close();
    }

    public void readBytes() throws IOException {
//        SocketChannel socketChannel = SocketChannel.open();
//        socketChannel.connect(new InetSocketAddress("haoma.com",80));
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9990));
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        channel.receive(buf);//接收Buf中的数据（读取）

        /**发送*/
        String newData = "new String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf1 = ByteBuffer.allocate(48);
        buf1.clear();
        buf1.put(newData.getBytes());
        buf1.flip();
        int bytesSent = channel.send(buf1, new InetSocketAddress("haoma.com", 80));

    }

    private static final int TIMEOUT = 3000;

    public static void main(String[] args) throws IOException {
        //打开服务端socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //打开selector
        Selector selector = Selector.open();
        //监听本地9999端口，并设置channel非阻塞
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(TIMEOUT) == 0) {
                System.out.println(".");
                continue;
            }
            //获取I/O操作就绪的SelectorKey,通过SelectorKey可以知道哪些channel的哪类I/0操作 就绪
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    SocketChannel clientChannel = ((ServerSocketChannel) next.channel()).accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(next.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(500));
                }
                if (next.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) next.channel();
                    ByteBuffer buf = (ByteBuffer) next.attachment();
                    long byteRead = clientChannel.read(buf);
                    if (byteRead == -1) {
                        clientChannel.close();
                    } else if (byteRead > 0) {
                        next.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        System.out.println("Get data length : " + byteRead);
                    }
                }
                if (next.isValid() && next.isWritable()) {//如果有效、可写
                    ByteBuffer buf = (ByteBuffer) next.attachment();
                    buf.flip();
                    SocketChannel clientChannel = (SocketChannel) next.channel();
                    clientChannel.write(buf);

                    if (!buf.hasRemaining()) {
                        next.interestOps(SelectionKey.OP_READ);
                    }
                    buf.compact();
                }
                //删除
                iterator.remove();
            }
        }

    }
}
