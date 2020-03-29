package br.com.nicemc.proxy.storage.redis.builder;

import lombok.Getter;
import redis.clients.jedis.JedisPool;

public class RedisConnectionPool {

    private String hostname;
    private int port;

    @Getter
    private JedisPool pool;

    public RedisConnectionPool(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void create() {
        this.pool = new JedisPool(hostname, port);
    }

    public boolean isConnected() {
        return !pool.isClosed();
    }

    public void disconnect() {
        pool.destroy();
    }

}
