package cn.magicnian.parallel;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch cdl = new CountDownLatch(2);
        //task1
        TaskOne taskOne = new TaskOne(cdl);
        Future<Object> resultOne =  executorService.submit(taskOne);

        //task2
        TaskTwo taskTwo = new TaskTwo(cdl);
        Future<Object> resultTwo = executorService.submit(taskTwo);

        //get result


        try {
            System.out.println("start");
            if(cdl.await(3,TimeUnit.SECONDS)){
                System.out.println("end");
                Object one = resultOne.get();
                Object two = resultTwo.get();
                System.out.println(one.toString());
                System.out.println(two.toString());
            }else{
                System.out.println("timeout");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
