package com.xt.socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    final static String ADDRESS = "127.0.0.1";
    final static int PORT = 8765;

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            //1、创建客户端Socket对象同时制定将要连接的服务器的ip地址和端口号
            socket = new Socket(ADDRESS, PORT);

            //2、设置目的：网络向服务器端发送数据
            out = new PrintWriter(socket.getOutputStream(), true);
            ;//此处也可以选择别的Io输出流，例如：BufferedWriter

            //3、设置目的：网络向服务器端发送数据
            out.println("接收到客户端的请求数据...");

            //4、创建接受服务器端返回数据的IO输入流
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //5、输出服务器返回的数据
            String response = in.readLine();
            System.out.println("Client: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    //6、关闭资源
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    //7、关闭资源
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    //8、关闭资源
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket = null;
        }
    }
}
