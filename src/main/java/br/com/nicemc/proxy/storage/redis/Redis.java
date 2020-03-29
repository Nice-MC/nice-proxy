package br.com.nicemc.proxy.storage.redis;

import br.com.nicemc.proxy.storage.redis.builder.RedisConnectionPool;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

public class Redis {

    private RedisConnectionPool connectionPool;

    public Redis(RedisConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Redis connect() {
        this.connectionPool.create();
        return this;
    }

    public void set(Map<String, String> increments) {
        Jedis jedis = getPool().getResource();
        increments.forEach(jedis::set);
    }

    public void set(String key, Object object) {
        Jedis jedis = getPool().getResource();
        jedis.set(key, new Gson().toJson(object));
    }

    public String get(String key) {
        Jedis jedis = getPool().getResource();
        return jedis.get(key);
    }

    public <T> T get(Class<T> clazz, String key) {
        Jedis jedis = getPool().getResource();
        return new GsonBuilder().create().fromJson(jedis.get(key), clazz);
    }

    public void del(String... keys) {
        Jedis jedis = getPool().getResource();
        jedis.del(keys);
    }

    public void flush() {
        Jedis jedis = getPool().getResource();
        jedis.flushAll();
    }

    public JedisPool getPool() {
        return connectionPool.getPool();
    }

}
