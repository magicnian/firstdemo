import cn.magicnian.Application;
import cn.magicnian.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class ApplicationTest {


    @Autowired
    private UserService userService;


    @Test
    @Transactional
    public void test()throws Exception{
        userService.create("c",12);
        userService.create("d",15);
        Thread.sleep(1000);

        throw new RuntimeException("test exception");
    }

}
