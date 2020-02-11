package cn.magicnian.timewheel;

public abstract class MyTimeTask implements Runnable{

    /**
     * 延迟时长，单位：秒
     */
    private int delays;

    /**
     * 在时间环中的索引
     */
    private int index;

    /**
     * 需要等待的圈数
     */
    private int cycles;

    public MyTimeTask(int delays) {
        this.delays = delays;
    }

    @Override
    public void run() {
    }

    public int getDelays() {
        return delays;
    }

    public void setDelays(int delays) {
        this.delays = delays;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

}
