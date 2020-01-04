package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {

    static class Task implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("执行中");
            return "success";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new Task());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
