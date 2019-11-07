package cn.magicnian.gc;

/**
 * 此代码演示了两点
 * 1.对象可以在被GC时自我拯救
 * 2.这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes,I am still alive");
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable{
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象的第一次成功自救
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以等待0.5秒
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no,I am dead");
        }

        //下面这段代码和上面的完全相同，但是这次自救却失败了

        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以等待0.5秒
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no,I am dead");
        }
    }
}
/**
 * 对象生存还是死亡-摘自深入理解java虚拟机
 *
 * 即使在可达性分析算法中不可达的对象，也并非时“非死不可”的，这时候它们暂时处于“缓刑”阶段
 * 要真正宣告一个对象死亡，至少要经历两次标记过程：
 * 如果对象在进行可达性分析后发现没有与GC Roots相连接的引用链，那它将会被第一次标记并且进行一次筛选
 * 筛选的条件是此对象是否有必要执行finalize()方法。当对象没有覆盖finalize()方法,或者finalize()方法已经被虚拟机调用过
 * 虚拟机将这两种情况都视为“没必要执行”
 * 如果这个对象被判定为没有必要执行finalize()方法，那么这个对象将会放置在一个叫做F-Queue的队列中
 * 并在稍后一个由虚拟机自动建立的、低优先级的Finalizer线程去执行它。
 * 这里所谓的“执行”是指虚拟机会触发这个方法，但并不承诺会等待它运行结束
 * 这样做的原因是，如果一个对象在finalize()方法中执行缓慢，或者发生了死循环（更极端的情况）
 * 将很可能会导致F-Queue队列中其他对象永久处于等待，甚至导致整个内存回收系统崩溃
 * finalize()方法是对象逃脱死亡命运的最后一次机会，稍后GC将对F-Queue中的对象进行第二次小规模的标记
 * 如果对象要在finalize()中成功拯救自己，只需要重新与引用链上的任何一个对象建立关联即可
 * 譬如把自己(this关键字)赋值给某个类变量或者对象的成员变量，那么在第二次标记时它将被移除出“即将回收”的集合
 * 如果对象这时候还没有脱逃，那基本商它就真的被回收了
 */
