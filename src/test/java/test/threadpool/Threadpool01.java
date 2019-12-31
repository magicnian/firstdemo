package test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threadpool01 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(()-> System.out.println("do something"));
    }
}
