package com.xt.socket.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class HandlerExecutorPool {

    private ExecutorService executor;

    public HandlerExecutorPool(int maxSize, int queueSize) {
        this.executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue <> (queueSize));
    }


    public void execute(Runnable task) {
        executor.execute(task);
    }


}
