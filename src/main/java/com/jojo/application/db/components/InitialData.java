package com.jojo.application.db.components;

import com.jojo.application.db.entity.Object;
import com.jojo.application.db.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitialData
{
    @Autowired
    private ObjectRepository repository;

    @EventListener
    public void appReady(ApplicationReadyEvent event)
    {
        Object root = new Object( null, "Top folder");
        repository.save(root);
    }
}
