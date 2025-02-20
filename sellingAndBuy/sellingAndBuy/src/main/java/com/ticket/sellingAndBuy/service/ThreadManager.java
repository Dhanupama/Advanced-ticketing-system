package com.ticket.sellingAndBuy.service;

import com.ticket.sellingAndBuy.entity.Customer;
import com.ticket.sellingAndBuy.entity.Vendor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadManager {

    private ExecutorService executorService;

    private boolean isRunning = false;

    public void start(Runnable task) {
        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newCachedThreadPool();
        }
        executorService.submit(task);
        isRunning = true;
    }

    public void stop(Runnable task) {
        if (task instanceof Vendor) {
            ((Vendor) task).stop();
        } else if (task instanceof Customer) {
            ((Customer) task).stop();
        }
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdownNow();
        }
        isRunning = false;
    }

    public void stopAll()
    {
        System.out.println("Stopping all threads");
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdownNow();
        }
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}