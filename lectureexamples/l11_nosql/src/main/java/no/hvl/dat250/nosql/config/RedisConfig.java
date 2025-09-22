package no.hvl.dat250.nosql.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import no.hvl.dat250.nosql.entities.Poll;
import no.hvl.dat250.nosql.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }

    @Bean
    public JsonMapper mapper() {
        JsonMapper m = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .addModule(new Jdk8Module())
                .build();
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        m.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        m.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return m;
    }


    @Bean
    public RedisTemplate<String, User> userTemplate(RedisConnectionFactory connectionFactory, JsonMapper mapper) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Set the serializer for the value
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(mapper, User.class);
        template.setDefaultSerializer(serializer);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }


    @Bean
    public RedisTemplate<String, Poll> pollTemplate(RedisConnectionFactory connectionFactory, JsonMapper mapper) {
        RedisTemplate<String, Poll> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Set the serializer for the value
        Jackson2JsonRedisSerializer<Poll> serializer = new Jackson2JsonRedisSerializer<>(mapper, Poll.class);
        template.setDefaultSerializer(serializer);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }





}
