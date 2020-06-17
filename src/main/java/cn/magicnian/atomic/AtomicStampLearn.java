package cn.magicnian.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampLearn {


    static class Counter {
        private AtomicStampedReference<Integer> count = new AtomicStampedReference<>(0, 0);

        public int getCount() {
            return count.getReference();
        }

        public int increment() {
            int[] stamp = new int[1];
            while (true) {
                Integer value = count.get(stamp); //同时获取时间戳和数据，防止获取到数据和版本不是一致的
                int newValue = value + 1;
                boolean writeOk = count.compareAndSet(value, newValue, stamp[0], stamp[0] + 1);
                if (writeOk)
                    return newValue;
            }
        }

        public int decrement() {
            int[] stamp = new int[1];
            while (true) {
                Integer value = count.get(stamp);//同时获取时间戳和数据，防止获取到数据和版本不是一致的
                int newValue = value - 1;
                boolean writeOk = count.compareAndSet(value, newValue, stamp[0], stamp[0] + 1);
                if (writeOk)
                    return newValue;
            }
        }
    }



    static class Counter2{
        private AtomicStampedReference<Integer> count = new AtomicStampedReference<Integer>(0, 0);

        public int getCount() {
            return count.getReference();
        }

        public int increment() {
            while(true) {
                //必须先获取stamp，然后取值，顺序不能反，否则仍然会有ABA的问题
                int stamp = count.getStamp();
                Integer value = count.getReference();
                int newValue = value + 1;
                boolean writeOk = count.compareAndSet(value, newValue, stamp, stamp + 1);
                if(writeOk) {
                    return newValue;
                }
            }
        }

        public int decrement() {
            while(true) {
                //必须先获取stamp，然后取值，顺序不能反，否则仍然会有ABA的问题
                int stamp = count.getStamp();
                Integer value = count.getReference();
                int newValue = value - 1;
                boolean writeOk = count.compareAndSet(value, newValue, stamp, stamp + 1);
                if(writeOk) {
                    return newValue;
                }
            }
        }
    }
}
