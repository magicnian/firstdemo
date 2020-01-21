//package cn.magicnian.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//
//
//    @Bean
//    public GenericObjectPoolConfig genericObjectPoolConfig(RedisProperties redisProperties){
//        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
//        genericObjectPoolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
//        genericObjectPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
//        genericObjectPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
//        genericObjectPoolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
//        return genericObjectPoolConfig;
//    }
//
//
//    @Bean
//    public RedisStandaloneConfiguration standaloneConfiguration(RedisProperties redisProperties){
//        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
//        standaloneConfiguration.setPort(redisProperties.getPort());
//        standaloneConfiguration.setHostName(redisProperties.getHost());
//        standaloneConfiguration.setDatabase(redisProperties.getDatabase());
//        return standaloneConfiguration;
//    }
//
//
//    @Bean("customJedisConnectionFactory")
//    public JedisConnectionFactory jedisConnectionFactory(RedisProperties redisProperties){
//        JedisClientConfiguration.DefaultJedisClientConfigurationBuilder builder = (JedisClientConfiguration.DefaultJedisClientConfigurationBuilder)JedisClientConfiguration.builder();
//        builder.connectTimeout(redisProperties.getTimeout());
//        builder.usePooling();
//        builder.poolConfig(genericObjectPoolConfig(redisProperties));
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(standaloneConfiguration(redisProperties),builder.build());
//        connectionFactory.afterPropertiesSet();
//        return connectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(@Qualifier("customJedisConnectionFactory") RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//
//        template.setConnectionFactory(factory);
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        // key采用String的序列化方式
//        template.setKeySerializer(stringRedisSerializer);
//        // hash的key也采用String的序列化方式
//        template.setHashKeySerializer(stringRedisSerializer);
//        // value序列化方式采用jackson
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        //hash的value序列化方式采用jackson
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }
//}
