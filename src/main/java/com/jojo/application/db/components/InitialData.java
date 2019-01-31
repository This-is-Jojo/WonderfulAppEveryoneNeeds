package com.jojo.application.db.components;

import com.jojo.application.db.entity.Object;
import com.jojo.application.db.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class InitialData
{
    @Autowired
    private ObjectRepository repository;

    @EventListener
    public void appReady(ApplicationReadyEvent event)
    {
        Object root = new Object(BigInteger.valueOf(10), null, "Top folder");
        Object child = new Object("Children", root.getObjectId());
        repository.save(root);
        repository.save(child);
    }
}
