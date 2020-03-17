package cn.magicnian.controller;


import cn.magicnian.model.Person;
import cn.magicnian.schedule.TaskContext;
import cn.magicnian.service.AsyncTestService;
import cn.magicnian.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private AsyncTestService asyncTestService;

    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam("uid") String uid) {
        TaskContext taskContext = TaskContext.getInstance();
        taskContext.addTask(taskContext.cutDownIndex(), uid);
        return "success";
    }


    @RequestMapping("/asyncTest")
    public String asyncTest(@RequestParam("phone") String phone) {
        asyncTestService.testFunc(phone);
        return "success";
    }

    @RequestMapping("/redisTest")
    public String redisTest(@RequestParam("key") String key) {
        Person p = new Person();
        p.setAge("12");
        p.setUid("testuid");
        p.setName("jjj");
        redisUtil.hset(key, "one", p);

        Object o = redisUtil.hget(key, "one");
        System.out.println("=========p hashcode:" + p.hashCode());

        System.out.println("=========o hashcode:" + o.hashCode());

        return "success";
    }

    @RequestMapping(value = "/validationtest1", method = RequestMethod.POST)
    public String vt1(@RequestBody User user) {
        try {
            user.nullFieldValidate();
        } catch (Exception e) {

        }

        return "sss";
    }
}
