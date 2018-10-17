package wiwi.qinj.utils;

import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-07 16:17
 */
@Slf4j
//@Service
public class RedisService {


    /**
     * 连接池
     */
    private static JedisPool jedisPool;
    @Autowired
    private Environment environment;
    private String redisServer = "redis.url";
    private String redisPort;

    @Value("${spring.cache.redis.psw}")
    private String redis_password;


    @PostConstruct
    private void initRedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(2000);
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(5);
        // 当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，抛出JedisConnectionException；
        config.setMaxWaitMillis(1000 * 2);
        //	在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(true);
        int timeout = 3000;
        int redisPort = Integer.valueOf(environment.getProperty("redisPort", "6379"));
        // 从系统环境中获取AES的加密password
        String neft_password = environment.getProperty("qinj_redis_psw");
        // redis passowrd 解密
        String redisPsw = AES.decrypt(redis_password, neft_password);
        jedisPool = new JedisPool(config, redisServer, redisPort, timeout, redisPsw);
    }

    public void batch_set(Map<String, String> key_val_map) {
        Jedis jedis = jedisPool.getResource();
        Pipeline pipeline = jedis.pipelined();
        if (key_val_map.isEmpty()) {
            // 空集，直接返回
            return;
        }
        for (String field : key_val_map.keySet()) {
            pipeline.set(field, key_val_map.get(field));
        }
        pipeline.sync();
        try {
            jedis.close();
        } catch (Exception e2) {
            log.warn("------batch_set exception------");
        }
    }

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(key, value);
            return result;
        } catch (Exception e) {
            log.warn("------jedis exception------", e);
            return null;
        } finally {
            try {
                jedis.close();
            } catch (Exception e2) {
            }
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.get(key);
            return result;
        } catch (Exception e) {
            log.warn("------get exception------", e);
            return null;
        } finally {
            jedis.close();
        }
    }

    // 批量插入数据
    public void hset_batch(String key, Map<String, String> keyValDatas) {
        if (keyValDatas.isEmpty()) {
            return;
        }
        Jedis jedis = jedisPool.getResource();
        Pipeline pipeline = jedis.pipelined();
        for (String field : keyValDatas.keySet()) {
            pipeline.hset(key, field, keyValDatas.get(field));
        }
        pipeline.sync();
        try {
            jedis.close();
        } catch (Exception e2) {
        }
    }

    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        try {
            jedis.close();
        } catch (Exception e2) {
        }
        return result;
    }

    public Map<String, String> hget(String key) {
        Jedis jedis = jedisPool.getResource();
        Map<String, String> result = jedis.hgetAll(key);
        try {
            jedis.close();
        } catch (Exception e2) {
        }
        return result;
    }

    public Long hdel(String key, String... fields) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, fields);
        try {
            jedis.close();
        } catch (Exception e2) {
        }
        return result;
    }

    public long hdelAllByKey(String hkey) {
        Jedis jedis = jedisPool.getResource();
        Set<String> set = jedis.hkeys(hkey);
        String[] strings = new String[set.size()];
        set.toArray(strings);
        Long result = 0L;
        if (strings.length > 0) {
            result = jedis.hdel(hkey, strings);
        }
        try {
            jedis.close();
        } catch (Exception e2) {
        }
        return result;
    }

}
