package com.jojo.application.db.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ObjectIdGenerator
{
    private AtomicLong LAST_TIME_MS;

    public ObjectIdGenerator()
    {
        this.LAST_TIME_MS = new AtomicLong();
    }

    public BigInteger generateObjectId()
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
                return BigInteger.valueOf(now);
            }
        }

    }
}
