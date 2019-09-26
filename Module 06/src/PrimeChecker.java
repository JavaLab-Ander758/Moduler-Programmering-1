import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrimeChecker {
    private ExecutorService pool;
    private boolean isPrime;
    private long number;
    private int numberOfThreads;
    private long[] startValues;
    private long[] endValues;

    private PrimeChecker(long number, int numberOfThreads) {
        this.number = number;
        this.numberOfThreads = numberOfThreads;
        init();

        /* Output */
        long startTime;
        System.out.printf("You have %s available processors in your system... \n\n", Runtime.getRuntime().availableProcessors());

        //region Multi-thread
        startTime = System.currentTimeMillis();
        System.out.printf("Checking if %s is a prime number, multithreaded (%s threads). \n", number, numberOfThreads);
        runThreads();
        while (!pool.isTerminated()) // Wait for threads to finish
            ;
        System.out.printf("%s is%sa prime number. \nExecution time: %s ms. \n\n", number, isPrime ? " " : " not ", System.currentTimeMillis() - startTime);
        //endregion

        //region Single-thread
        startTime = System.currentTimeMillis();
        System.out.printf("Checking if %s is a prime number, singlethreaded. \n", number);
        System.out.printf("%s is%sa prime number. \nExecution time: %s ms.", number, singlethreadedPrimeCheck() ? " " : " not ", System.currentTimeMillis() - startTime);
        //endregion
    }

    public static void main(String[] args){
        PrimeChecker primeChecker = new PrimeChecker(9223372036854775783L, 10);
    }

    private void init(){
        startValues = new long[numberOfThreads];
        endValues = new long[numberOfThreads];

        pool = Executors.newFixedThreadPool(numberOfThreads);
        long iterator = (long)(Math.sqrt(number) / numberOfThreads);

        // Partition startValues[] and endValues[] for the threads to compute
        for (int i = 0; i < numberOfThreads; i++) {
            if (i == 0) {
                startValues[i] = 3;
                endValues[i] = startValues[i] + iterator;
            } else {
                startValues[i] = endValues[i - 1] + 1;
                endValues[i] = startValues[i] + iterator;
            }
            //System.out.printf("[%s] start: %s, end: %s\n", i, startValues[i], endValues[i]);
        }

    }
    private void runThreads(){
        /* execute i PrimeTasks*/
        try {
            for (int i = 0; i < numberOfThreads; i++)
                pool.execute(new PrimeTask(number, startValues[i], endValues [i]));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Could not execute the threads in the pool...");
        }
    }
    private void notPrime(){
        pool.shutdown();
        isPrime = false;
    }
    private boolean singlethreadedPrimeCheck(){
        /* Returns true if number is prime */
        if (number % 2 == 0)
            return false;
        for (long i = 3; i <= Math.sqrt(number); i = i+2)
            if (number % i == 0)
                return false;
        return true;
    }

    class PrimeTask implements Runnable {
        private long number;
        private long startValue; // Where to start checking if number is prime
        private long endValue; // Where to stop checking if number is prime

        PrimeTask(long number, long startValue, long endValue){
            this.number = number;
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            if (number % 2 == 0)
                notPrime();

            for (long i = startValue; i <= endValue; i = i+2)
                if (number % i == 0)
                    notPrime();

            if (endValue == endValues[numberOfThreads - 1]) { // If current thread is last thread, shutdown and isPrime is set to true
                pool.shutdown();
                isPrime = true;
            }
        }
    }
}