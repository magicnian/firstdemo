package cn.magicnian.tl;

public class ThreadLocalDemo {

    private static final ThreadLocal<Integer> th = ThreadLocal.withInitial(() -> 0);

    static Integer get(){
        return th.get();
    }

    static void set(Integer value){
        th.set(value);
    }


    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set(finalI);
                    System.out.println(Thread.currentThread().getName()+" result:"+get());
                }
            },"t"+String.valueOf(i)).start();
        }
    }
}
