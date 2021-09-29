package com.hwua.erhai.client;

import com.hwua.erhai.server.ServerDispatchRequest;
import com.hwua.erhai.view.UserStartView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 可以向服务器发送请求的类 一次请求建立一次连接
 *
 * @author Alain
 */
public class Client {
    private static final boolean DEBUG = false;

    private Socket socket = null;
    private BufferedReader brSocket;
    private PrintWriter pwSocket;

    /**
     * 客户端入口
     */
    public void start() {
    }

    /**
     * 与服务器建立连接
     */
    private void connect() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            brSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pwSocket = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException("failed to connect to server", e);
        }
    }

    /**
     * 释放资源
     */
    private void close() {
        if (brSocket != null) {
            try {
                brSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (pwSocket != null) {
            pwSocket.close();
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向服务器端发送一次请求
     *
     * @param request 请求字符串
     * @return 响应字符串
     */
    public String request(String request) {
        if (DEBUG) {
            return requestMockServer(request);
        }

        String response = null;
        //建立连接
        connect();
        //发送请求到服务器
        pwSocket.println(request);
        //读取响应
        try {
            response = brSocket.readLine();
        } catch (IOException e) {
            throw new RuntimeException("failed to read response", e);
        } finally {
            //释放资源
            close();
        }
        return response;
    }

    /**
     * 模拟向服务器端发送一次请求，用于开发阶段方便调试
     *
     * @param request 请求字符串
     * @return 响应字符串
     */
    public String requestMockServer(String request) {
        return new ServerDispatchRequest(null).dispatch(request);
    }

    public static void main(String[] args) {
        Client client = new UserStartView();
        client.start();
    }
}
