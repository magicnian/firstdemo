package cn.magicnian.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {

    private LuService luService;

    public Service(){
        System.out.println("service create");
    }

    public void test(){
        System.out.println(luService);
    }

    @Autowired
    public void setLuService(LuService luService){
        System.out.println("注入luservice by setter");
        this.luService = luService;
    }
}
