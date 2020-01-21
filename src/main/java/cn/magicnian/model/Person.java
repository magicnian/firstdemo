package cn.magicnian.model;

import lombok.Data;

@Data
public class Person {

    private String uid;
    private String name;
    private String age;
    private String email;


    @Override
    public boolean equals(Object o) {
        return this.uid.equals(((Person) o).getUid());
    }

    @Override
    public int hashCode() {
        return this.getUid().hashCode();
    }
}
