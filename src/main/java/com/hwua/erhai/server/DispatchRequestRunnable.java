package com.hwua.erhai.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 要处理一次请求的代码
 *
 * @author Alain
 */
public abstract class DispatchRequestRunnable implements Runnable {
    private Socket socket = null;
    private BufferedReader brSocket;
    private PrintWriter pwSocket;

    public DispatchRequestRunnable(Socket socket) {
        if (socket != null) {
            init(socket);
        }
    }

    private void init(Socket socket) {
        if (socket == null) {
            System.out.println("socket is null, init nothing");
            return;
        }
        this.socket = socket;
        try {
            brSocket = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            pwSocket = new PrintWriter(
                    socket.getOutputStream(), true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        if (socket == null) {
            System.out.println("socket is null, run nothing");
            return;
        }
        try {
            //读取请求
            String request = brSocket.readLine();
            //处理
            String response = dispatch(request);
            //写出响应
            pwSocket.println(response);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close();
        }

        //释放资源
    }

    public abstract String dispatch(String request);
	/*{
		String response = null;
		//具体的处理
		//================
		return response;
	}*/

    public void close() {
        if (brSocket != null) {
            try {
                brSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
