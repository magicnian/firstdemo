package cn.magicnian.controller;


import cn.magicnian.schedule.TaskContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam("uid") String uid) {
        TaskContext taskContext = TaskContext.getInstance();
        taskContext.addTask(taskContext.cutDownIndex(),uid);
        return "success";
    }
}
