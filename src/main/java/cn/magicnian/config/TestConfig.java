package cn.magicnian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TestConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer testProperties(){
        PropertySourcesPlaceholderConfigurer ps = new PropertySourcesPlaceholderConfigurer();
        ps.setIgnoreResourceNotFound(true);
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(new ClassPathResource("properties/test.properties"));
        resourceList.add(new ClassPathResource("properties/redis.properties"));
        ps.setLocations(resourceList.toArray(new Resource[]{}));
        return ps;
    }

}
