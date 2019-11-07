package cn.magicnian.controller;


import cn.magicnian.schedule.TaskContext;
import cn.magicnian.service.AsyncTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private AsyncTestService asyncTestService;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam("uid") String uid) {
        TaskContext taskContext = TaskContext.getInstance();
        taskContext.addTask(taskContext.cutDownIndex(), uid);
        return "success";
    }


    @RequestMapping("/asyncTest")
    public String asyncTest() {
        asyncTestService.testFunc();
        return "success";
    }
}
