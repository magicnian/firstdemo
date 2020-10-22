package cn.magicnian.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into user(name, age ) values(?, ?)", name, age);
    }


}
