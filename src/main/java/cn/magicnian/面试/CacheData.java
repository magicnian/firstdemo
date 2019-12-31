package cn.magicnian.面试;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheData {

    private Map<String, String> cache = new HashMap<>();

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock readLock = rwl.readLock();

    private ReentrantReadWriteLock.WriteLock writeLock = rwl.writeLock();

    /**
     * 获取缓存值
     * 如果缓存中不存在，更新缓存再返回
     *
     * @param key
     * @return
     */
    public String doCache(String key) {
        try {
            //读取缓存时获取读锁
            readLock.lock();
            String value = cache.get(key);
            //缓存不存在，需要更新缓存
            if (value == null) {
                //释放读锁后再尝试获取写锁，必须释放，不然无法获取写锁，造成死锁
                readLock.unlock();
                try {
                    //尝试获取写锁
                    writeLock.lock();
                    value = cache.get(key);
                    //再做一次判断，可能有其他的写线程更新过了缓存
                    if (value == null) {
                        value = Thread.currentThread().getName() + "---value";
                        cache.put(key, value);
                        System.out.println(Thread.currentThread().getName()+"--------------put  "+value);
                    }
                    return value;
                } finally {
                    System.out.println(Thread.currentThread().getName()+"----写锁unlock");
                    writeLock.unlock();
                }
            }
            return value;
        } finally {
            System.out.println(Thread.currentThread().getName()+"-----读锁unlock");
            try {
                readLock.unlock();
            }catch (Exception e){

            }

        }
    }
}
