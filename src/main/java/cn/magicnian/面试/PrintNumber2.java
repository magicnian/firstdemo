package cn.magicnian.面试;

/**
 * 99个线程依次打印1至99
 * 比较简单的实现方式
 */
public class PrintNumber2 {

    public static volatile int number;

    public static final Object object = new Object();


    static class Task implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(++number);
            }
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<99;i++){
            new Thread(new Task()).start();
        }
    }
}
