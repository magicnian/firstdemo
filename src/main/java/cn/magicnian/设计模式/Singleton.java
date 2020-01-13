package cn.magicnian.设计模式;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 通过cas的方式实现单例
 * 感觉没啥好处，虽然不用锁了，
 * 但是在执行 singleton = new Singleton()时
 * 如果有大量的线程执行，会产生大量的无用对象
 * 可能会导致内存溢出
 * 最好的单例实现方式还是通过枚举实现
 */
public class Singleton {

    private static final AtomicReference<Singleton> INSTANCE = new AtomicReference<>();

    private Singleton(){

    }

    public static Singleton getInstance(){
        for(;;){
            Singleton singleton = INSTANCE.get();

            if(null != singleton){
                return singleton;
            }

            singleton = new Singleton();
            if(INSTANCE.compareAndSet(null,singleton)){
                return singleton;
            }
        }
    }
}
