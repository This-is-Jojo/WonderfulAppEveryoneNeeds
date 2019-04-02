package com.jojo.application.controller;

import com.jojo.application.db.entity.ObjectType;
import com.jojo.application.db.repository.ObjectTypeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ObjectTypeController
{
    final private ObjectTypeRepository objectTypes;

    public ObjectTypeController(ObjectTypeRepository repository)
    {
        this.objectTypes = repository;
    }

    @GetMapping("/objectTypes/{objectTypeId}")
    public ObjectType getObjectType(@PathVariable long objectTypeId)
    {
        return this.objectTypes.getOne(objectTypeId);
    }

    @PostMapping("/objectTypes")
    public Object createObject(@RequestBody ObjectType objectType)
    {
        return objectTypes.save(objectType);
    }

    @PutMapping("/objectTypes/{objectTypeId}")
    public Object updateObject(@PathVariable Long objectTypeId,
                               @RequestBody ObjectType objectRequest)
    {
        return objectTypes.findById(objectTypeId).map(object -> {
            object.setName(objectRequest.getName());
            object.setName(objectRequest.getName());
            return objectTypes.save(object);
        }).orElseThrow(() -> new RuntimeException("Cannot update objectType with objectTypeId = " + objectTypeId));
    }
}
