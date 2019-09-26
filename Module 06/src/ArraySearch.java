import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArraySearch {
    private ExecutorService pool;
    private int numberOfThreads;
    private long[] numArray;
    private long toFind;
    private long startTime;

    private ArraySearch(long arrayLength, long toFind, int numberOfThreads){
        numArray = new long[(int)arrayLength];
        this.toFind = toFind;
        this.numberOfThreads = numberOfThreads;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Array size?: ");
        long arrayLength = scanner.nextLong();
        System.out.printf("(%s - %s), which number do you want to find?", 1, arrayLength);
        long toFind = scanner.nextLong();
        System.out.print("Number of threads: ");
        int numberOfThreads = scanner.nextInt();

        ArraySearch arraySearch = new ArraySearch(arrayLength, toFind, numberOfThreads);
        //arraySearch.calculate();
        arraySearch.initializeThreads();
        arraySearch.runThreads();
    }

    private void calculate() {
        initializeThreads();
        runThreads();
        //while (!pool.isTerminated())
        //    ;
    }

    private void initializeThreads() {
        pool = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = i + 1;
            System.out.println(numArray[i]);
        }
    }
    private void runThreads() {
        /* Run threads with partized indexes of numArray*/
        long iterator = (long)numArray.length / numberOfThreads;
        System.out.printf("numArray.length: %s, numberOfThreads: %s, iterator: %s \n", numArray.length, numberOfThreads, iterator);
        long start = 0;

        /* new ThreadTasks with partitioned indexes for numArray */
        for (int i = 0; i < numberOfThreads; i++) {
            if (i != 0)
                start += iterator;
            pool.execute(new ThreadTask(start));
            //System.out.printf("(%s - %s): [%s] \n", start, start+iterator, i+1);
        }
    }

    class ThreadTask implements Runnable {
        private long searchIndex;

        ThreadTask(long searchIndex) {
            this.searchIndex = searchIndex;
        }

        @Override
        public void run() {
            for (long i = searchIndex; i < searchIndex*2-1; i++)
                if (numArray[(int)i] == toFind) {
                    System.out.printf("\nfound: numArray[%s] = ", i, numArray[(int)i]);
                    pool.shutdown();
                }
        }
    }
}