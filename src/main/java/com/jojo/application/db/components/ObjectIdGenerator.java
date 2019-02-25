package com.jojo.application.db.components;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class ObjectIdGenerator
{
    private static class InstanceHolder {
        public static ObjectIdGenerator instance = new ObjectIdGenerator();
    }

    public static ObjectIdGenerator getInstance() {
        return InstanceHolder.instance;
    }

    private AtomicLong LAST_TIME_MS = new AtomicLong();

    public long generate()
    {
        long now = System.nanoTime();
        while (true)
        {
            long lastTime = LAST_TIME_MS.get();
            if (lastTime >= now)
            {
                now = lastTime + 1;
            }
            if (LAST_TIME_MS.compareAndSet(lastTime, now))
            {
                return now;
            }
        }
    }
}
