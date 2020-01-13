import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setName("张三");
        userInfo1.setUgid("001");
        userInfo1.setToken("t1");


        UserInfo userInfo2 = new UserInfo();
        userInfo2.setName("李四");
        userInfo2.setUgid("002");
        userInfo2.setToken("t2");


        UserInfo userInfo3 = new UserInfo();
        userInfo3.setName("张三");
        userInfo3.setUgid("001");
        userInfo3.setToken("t3");

        UserInfo userInfo4 = new UserInfo();
        userInfo4.setName("tom");
        userInfo4.setUgid("003");
        userInfo4.setToken("t4");

        UserInfo userInfo5 = new UserInfo();
        userInfo5.setName("张三");
        userInfo5.setUgid("001");
        userInfo5.setToken("t5");

        List<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(userInfo1);
        userInfos.add(userInfo2);
        userInfos.add(userInfo3);
        userInfos.add(userInfo4);
        userInfos.add(userInfo5);

        Map<String,List<UserInfo>> groupResult =  userInfos.stream().collect(Collectors.groupingBy(UserInfo::getUgid));

        System.out.println(groupResult);

        long count = groupResult.size();

        System.out.println(count);

    }

    @Data
    static class UserInfo{
        private String name;
        private String token;
        private String ugid;

    }
}
