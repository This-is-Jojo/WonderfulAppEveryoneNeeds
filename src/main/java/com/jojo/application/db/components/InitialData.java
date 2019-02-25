package com.jojo.application.db.components;

import com.jojo.application.db.entity.Object;
import com.jojo.application.db.repository.ObjectRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitialData
{
    private ObjectRepository repository;

    public InitialData(ObjectRepository repository)
    {
        this.repository = repository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event)
    {
        Object root = new Object(10L, "Top folder", null);
        Object documents = new Object("Documents", root.getObjectId());
        Object inventory = new Object("Inventory", root.getObjectId());
        Object testDocument = new Object("Test Document", documents.getObjectId());

        repository.save(root);
        repository.save(documents);
        repository.save(inventory);
        repository.save(testDocument);
    }
}
