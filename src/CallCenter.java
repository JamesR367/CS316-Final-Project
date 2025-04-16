import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class CallCenter {
    private static final int CUSTOMERS_PER_AGENT = 5;
    private static final int NUMBER_OF_AGENTS = 3;
    private final static Queue<Integer> waitQueue = new LinkedList<>();
    private final static Queue<Integer> serveQueue = new LinkedList<>();


    private static final int NUMBER_OF_CUSTOMERS = NUMBER_OF_AGENTS * CUSTOMERS_PER_AGENT;
    private static final int NUMBER_OF_THREADS = 10;

    private final static ReentrantLock greeterLock = new ReentrantLock();
    private final static ReentrantLock agentLock = new ReentrantLock();
    private final static ReentrantLock lock = new ReentrantLock();
    private final static Condition wQueueIsEmpty = lock.newCondition();
    private final static Condition sQueueIsEmpty = lock.newCondition();


    public static class Agent implements Runnable {
    //TODO: complete the agent class
        //The ID of the agent
        private final int ID;

        //Feel free to modify the constructor
        public Agent(int i) {
            ID = i;
        }
        /*
        Your implementation must call the method below to serve each customer.
        Do not modify this method.
         */
        public void serve(int customerID) {
            System.out.println("Agent " + ID + " is serving customer " + customerID);
            try {
                /*
                   Simulate busy serving a customer by sleeping for a random amount of time.
                */
                sleep(ThreadLocalRandom.current().nextInt(10, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Greeter implements Runnable{
        public void run() {
            try {
                while (waitQueue.isEmpty()) {
                    wQueueIsEmpty.await();
                }
                greeterLock.lock();
                int Customer =  waitQueue.remove();
                greet(Customer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                greeterLock.unlock();
            }
        }

        public void greet(int customerID) {
            System.out.println("Greeting customer " + customerID);
                try {
                    sleep(ThreadLocalRandom.current().nextInt(10, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }

    public static class Customer implements Runnable {
        private int ID;
        public Customer(int id){this.ID = id;}
        public void run() {
            waitQueue.add(ID);
            wQueueIsEmpty.signal();
        }
    }

    /*
        Create the greeter and agents tasks first, and then create the customer tasks.
        to simulate a random interval between customer calls, sleep for a random period after creating each customer task.
     */
    public static void main(String[] args){
        ExecutorService es = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        es.submit(new Greeter());

        for (int i = 0; i < NUMBER_OF_AGENTS; i++) {
            es.submit(new Agent(i));
        }

        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            es.submit(new Customer(i));
        }
    }

}
