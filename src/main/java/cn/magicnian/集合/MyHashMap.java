package cn.magicnian.集合;

public class MyHashMap<k,v> {

    private Entry[] table;

    private static Integer CAPCITY = 8;

    private int size = 0;

    public MyHashMap() {
        this.table = new Entry[CAPCITY];
    }

    public v get(k key){
        int hashCode = key.hashCode();
        int index = hashCode % CAPCITY;

        for(Entry<k,v> entry = table[index];entry!=null;entry = entry.next){
            if(entry.k.equals(key)){
                return entry.v;
            }
        }

        return null;
    }

    public v put(k key,v value){

        int hashCode = key.hashCode();
        int index = hashCode % CAPCITY;

        for(Entry<k,v> entry = table[index];entry!=null;entry = entry.next){
            if(entry.k.equals(key)){
                v oldValue = entry.v;
                entry.v = value;
                return oldValue;
            }
        }

        Entry entry = new Entry(key,value,table[index]);
        table[index] = entry;
        size++;
        return null;

    }

    public int size(){
        return size;
    }

    class Entry<k,v>{
        private k k;
        private v v;
        private Entry next;

        public Entry(k k, v v, Entry next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public k getK() {
            return k;
        }

        public v getV() {
            return v;
        }

        public Entry getNext() {
            return next;
        }
    }


}
