package com.xt.socket.test;

import com.xt.socket.test.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    final static int PROT = 8765;

    public static void main(String[] args) {

        ServerSocket server = null;
        try {
            /* 1. 建立用于监听和接受客户端连接请求的套接字 */
            server = new ServerSocket(PROT);
            System.out.println(" server start .. ");
			/*accept()接受一个客户端的连接请求，并返回一个新的套接字。
			所谓“新的”就是说这个套接字与socket()返回的用于监听和接受客户端的连接请求的套接字不是同一个套接字。
			与本次接受的客户端的通信是通过在这个新的套接字上发送和接收数据来完成的。

			再次调用accept()可以接受下一个客户端的连接请求，
			并再次返回一个新的套接字（与socket()返回的套接字、之前accept()返回的套接字都不同的新的套接字）。
			这个新的套接字用于与这次接受的客户端之间的通信。*/
            /*
             * 2.进行阻塞
             * 等待客户端连接请求，在没有客户端连接请求到来之前，
             * 程序会一直阻塞在这个函数里。
             */
            Socket socket = null;
            //3.新建一个线程执行客户端的任务
           // new Thread(new ServerHandler(socket)).start();

            HandlerExecutorPool pool = new HandlerExecutorPool(50, 1000);
            while (true) {
                socket = server.accept();
                pool.execute(new ServerHandler(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            server = null;
        }


    }
}