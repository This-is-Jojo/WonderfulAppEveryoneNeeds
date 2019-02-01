package com.jojo.application.db.components;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ObjectIdGenerator implements IdentifierGenerator
{
    private AtomicLong LAST_TIME_MS = new AtomicLong();

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException
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
