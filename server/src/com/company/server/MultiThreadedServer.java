package com.company.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {

    protected int serverPort = 9006;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    @Override
    public void run() {
        openServerSocket();

        while (!isStopped()) {
            Socket clientSocket = null;

            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Сервер остановлен");
                    return;
                }

                throw new RuntimeException("Ошибка соединения", e);
            }

            System.out.println(clientSocket);
            new Thread(new Worker(clientSocket)).start();
        }

        System.out.println("Сервер остановлен");
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;

        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка закрытия сервера", e);
        }
    }

    private void openServerSocket() {
        System.out.println("Открытие сервер сокета..");

        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка открытия на порту " + this.serverPort, e);
        }
    }

}