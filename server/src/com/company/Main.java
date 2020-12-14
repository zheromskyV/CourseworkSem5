package com.company;

import com.company.server.*;

public class Main {
    public static final int PORT_WORK = 9006;

    public static void main(String[] args) {
        System.out.println("\n\n\n");
        MultiThreadedServer server = new MultiThreadedServer(PORT_WORK);
        new Thread(server).start();
    }
}
