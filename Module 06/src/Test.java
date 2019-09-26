import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test implements Runnable {
    public static void main(String[] args) {
        Test t = new Test();

        ExecutorService executor = Executors.newCachedThreadPool();
    }

    public void run() {}
}