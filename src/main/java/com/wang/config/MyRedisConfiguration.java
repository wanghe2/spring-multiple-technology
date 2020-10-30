package com.wang.config;

import com.alibaba.fastjson.JSON;
import com.wang.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

@Configuration
public class MyRedisConfiguration {

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;

    @Bean("employeeRedisTemplate")
   // @ConditionalOnBean(name="")
    public RedisTemplate<String, Employee> employeeRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Employee> employeeRedisTemplate = new RedisTemplate<>();
        StringRedisSerializer strSerializer = new StringRedisSerializer();
        JsonRedisSerializer<Employee>jsonRedisSerializer = new JsonRedisSerializer<>(Employee.class);
        employeeRedisTemplate.setConnectionFactory(redisConnectionFactory);
        employeeRedisTemplate.setKeySerializer(strSerializer);
        employeeRedisTemplate.setHashKeySerializer(strSerializer);
        employeeRedisTemplate.setValueSerializer(jsonRedisSerializer);
        employeeRedisTemplate.setHashValueSerializer(jsonRedisSerializer);
        return employeeRedisTemplate;
    }


}

/**
 * 自定义序列化类（json型），用于value的序列化，支持泛型
 * @param <T>
 */
class JsonRedisSerializer<T> implements RedisSerializer<T>{

    Class<T>clazz = null;

    public JsonRedisSerializer(Class<T> tclass){
        this.clazz = tclass ;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(t == null){
            return new byte[0];
        }
        return JSON.toJSONString(t).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if(bytes != null && bytes.length>0){
            return (T)JSON.parseObject(new String(bytes,Charset.forName("UTF-8")),this.clazz);
        }
        return null;
    }
}
