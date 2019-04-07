package com.xt.socket.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DirectTest {

    public static void main(String[] args) throws Exception {
        /*LiftOff liftOff = new LiftOff();
        liftOff.run();*/

        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("waiting for lifting off");

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i <10 ; i++) {

        }
    }
}
