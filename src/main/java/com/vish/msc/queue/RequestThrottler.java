package com.vish.msc.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RequestThrottler {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public boolean lockResource(String requestOrigin) {
        boolean locked = redisTemplate.boundValueOps(requestOrigin).setIfAbsent("locked");
        if (locked) {
            redisTemplate.expire(requestOrigin, 500, TimeUnit.MILLISECONDS);
            return true;
        }
        return false;
    }

    public boolean unlockResource(String requestOrigin) {
        return redisTemplate.delete(requestOrigin);
    }

    public boolean throttleRequestCount(String requestOrigin, int throttleLimit) {
        int minutes = Calendar.getInstance().get(Calendar.MINUTE);
        while (!lockResource(requestOrigin)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String requestOriginMinute = requestOrigin + minutes;
        Long requestCount = (Long) redisTemplate.execute(
                new RedisCallback() {
                    @Override
                    public Object doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        connection.multi();
                        connection.stringCommands().incr(requestOriginMinute.getBytes());
                        connection.expire(requestOriginMinute.getBytes(), 90);
                        List<Object> results = connection.exec();
                        return results.get(0);
                    }
                }
        );
        unlockResource(requestOrigin);
        return (requestCount <= throttleLimit);
    }
}
