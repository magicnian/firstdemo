package test;

public class CloneTest implements Cloneable{
    private byte[] a = {1, 2, 3, 4};
    private byte[] b = {5, 6, 7, 8};

    public CloneTest clone() {
        CloneTest copy = null;
        try {
            copy = (CloneTest) super.clone();
            copy.a = this.a.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return copy;
    }
}
