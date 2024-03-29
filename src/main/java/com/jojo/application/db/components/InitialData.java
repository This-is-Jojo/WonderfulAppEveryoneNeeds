package com.jojo.application.db.components;

import com.jojo.application.db.entity.Attribute;
import com.jojo.application.db.entity.Object;
import com.jojo.application.db.entity.ObjectType;
import com.jojo.application.db.entity.Parameter;
import com.jojo.application.db.repository.AttributeRepository;
import com.jojo.application.db.repository.ObjectRepository;
import com.jojo.application.db.repository.ObjectTypeRepository;
import com.jojo.application.db.repository.ParametersRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitialData
{
    private ObjectRepository repository;
    private ObjectTypeRepository objectTypeRepository;
    private AttributeRepository attributeRepository;
    private ParametersRepository parametersRepository;

    public InitialData(ObjectRepository repository, ObjectTypeRepository objectTypeRepository, AttributeRepository attributeRepository, ParametersRepository parametersRepository)
    {
        this.repository = repository;
        this.objectTypeRepository = objectTypeRepository;
        this.attributeRepository = attributeRepository;
        this.parametersRepository = parametersRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event)
    {
        ObjectType folderObjectType = new ObjectType("Folder");
        ObjectType deviceObjectType = new ObjectType("Device");

        Attribute description = new Attribute(folderObjectType.getObjectTypeId(), "Description");
        Attribute someAttr = new Attribute(folderObjectType.getObjectTypeId(), "Test attribute");

        folderObjectType.addAtribute(description);
        folderObjectType.addAtribute(someAttr);

        Attribute deviceDescription = new Attribute(deviceObjectType.getObjectTypeId(), "Description");
        Attribute deviceModel = new Attribute(deviceObjectType.getObjectTypeId(), "Model");

        deviceObjectType.addAtribute(deviceDescription);
        deviceObjectType.addAtribute(deviceModel);

        Object root = new Object(10L, "Top folder", folderObjectType.getObjectTypeId(), null);
        Object documents = new Object("Documents", folderObjectType.getObjectTypeId(), root.getObjectId());
        Object inventory = new Object("Inventory", folderObjectType.getObjectTypeId(), root.getObjectId());
        Object testDocument = new Object("Test Document", folderObjectType.getObjectTypeId(), documents.getObjectId());

        root.setParameterValue(description.getAttrId(), "Test Description");
        root.setParameterValue(someAttr.getAttrId(), "Test Attribute Value");

        objectTypeRepository.save(folderObjectType);
        objectTypeRepository.save(deviceObjectType);

        repository.save(root);
        repository.save(documents);
        repository.save(inventory);
        repository.save(testDocument);
    }
}
