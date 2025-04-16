import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Thread.sleep;

public class CallCenter {
    private static final int CUSTOMERS_PER_AGENT = 5;
    private static final int NUMBER_OF_AGENTS = 3;

    private static final int NUMBER_OF_CUSTOMERS = NUMBER_OF_AGENTS * CUSTOMERS_PER_AGENT;
    private static final int NUMBER_OF_THREADS = 10;
    private static Semaphore greeterLock = new Semaphore(1);
    private static Semaphore agentLock = new Semaphore(NUMBER_OF_AGENTS);
    private static Semaphore custemerPerAgentLock = new Semaphore(CUSTOMERS_PER_AGENT);


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

    /*
        The greeter class.
     */
    public static class Greeter implements Runnable{
    //TODO: complete the Greeter class

     /*
        Your implementation must call the method below to serve each customer.
        Do not modify this method.
         */
        public void greet(int customerID) {
            System.out.println("Greeting customer " + customerID);
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

    /*
        The customer class.
     */
    public static class Customer implements Runnable {
        public void run() {
            try {

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        Create the greeter and agents tasks first, and then create the customer tasks.
        to simulate a random interval between customer calls, sleep for a random period after creating each customer task.
     */
    public static void main(String[] args){
    //TODO: complete the main method
    }

}
