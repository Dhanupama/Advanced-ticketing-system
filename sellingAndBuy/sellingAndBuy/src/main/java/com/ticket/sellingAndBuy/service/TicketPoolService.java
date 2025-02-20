package com.ticket.sellingAndBuy.service;

import com.ticket.sellingAndBuy.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketPoolService {

    private final AtomicInteger soldTicketCount;
    private final AtomicInteger addedTicketCount;
    private final List<String> ticketList;
    private final Admin admin;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    @Autowired
    private ThreadManager threadManager;

    public TicketPoolService( Admin admin) {
        this.soldTicketCount = new AtomicInteger(0);
        this.addedTicketCount = new AtomicInteger(0);
        this.ticketList = Collections.synchronizedList(new ArrayList<>());
        this.admin = admin;
    }

    public void addTicket(String ticket) throws InterruptedException {
        lock.lock();
        try {
            while (ticketList.size() >= admin.getMaxTicketCapacity()) {
                notFull.await();
            }
            if ( addedTicketCount.get() >= admin.getTotalTicket()) {
                System.out.println("Total Ticket count reached, failed to add ticket " + ticket);
                threadManager.stopAll();
//                Thread.currentThread().interrupt();
            }
            Thread.sleep(admin.getTicketRetrivalTime());
            ticketList.add(ticket);
            addedTicketCount.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " added ticket: " + ticket);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String removeTicket() throws InterruptedException {
        lock.lock();
        try {
            if (soldTicketCount.get() >= admin.getTotalTicket()) {
                System.out.println("Total Ticket count reached, failed to remove ticket");
                threadManager.stopAll();
//                Thread.currentThread().interrupt();
            }
            while (ticketList.isEmpty()) {
                notEmpty.await();
            }
            Thread.sleep(admin.getCustomerRetrivalTime());
            String ticket = ticketList.remove(0);
            soldTicketCount.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " removed ticket: " + ticket);
            notFull.signalAll();
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    public int getCurrentTicketId() {
        return addedTicketCount.get();
    }
}