package com.jojo.application.controller;

import com.jojo.application.db.entity.Object;
import com.jojo.application.db.entity.ObjectType;
import com.jojo.application.db.repository.ObjectRepository;
import com.jojo.application.db.repository.ObjectTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ObjectTypeController
{
    final private ObjectTypeRepository objectTypes;
    final private ObjectRepository objects;

    public ObjectTypeController(ObjectTypeRepository repository, ObjectRepository objects)
    {
        this.objectTypes = repository;
        this.objects = objects;
    }

    @GetMapping("/objectTypes/{objectTypeId}")
    public ObjectType getObjectType(@PathVariable long objectTypeId)
    {
        return this.objectTypes.getOne(objectTypeId);
    }

    @GetMapping("/objectTypes")
    public List<ObjectType> getObjectTypes()
    {
        return this.objectTypes.findAll();
    }

    @PostMapping("/objectTypes")
    public ObjectType createObjectType(@RequestBody ObjectType objectType)
    {
        return objectTypes.save(objectType);
    }

    @PutMapping("/objectTypes/{objectTypeId}")
    public ObjectType updateObjectType(@PathVariable Long objectTypeId,
                                       @RequestBody ObjectType objectRequest)
    {
        return objectTypes.findById(objectTypeId).map(objectType -> {
            objectType.setName(objectRequest.getName());
            return objectTypes.save(objectType);
        }).orElseThrow(() -> new RuntimeException("Cannot update objectType with objectTypeId = " + objectTypeId));
    }

    @DeleteMapping("/objectTypes/{objectTypeId}")
    public ResponseEntity deleteObjectType(@PathVariable Long objectTypeId)
    {
        List<Object> objectsUsingObjectType = objects.getObjectsByObjectTypeId(objectTypeId);
        if(!objectsUsingObjectType.isEmpty())
        {
            return ResponseEntity.badRequest()
                    .body("Cannot delete Object Type that is currently used by objects: " + objectsUsingObjectType);
        }

        return objectTypes.findById(objectTypeId).map(objectType -> {
            objectTypes.delete(objectType);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Cannot delete objectType with objectTypeId = " + objectTypeId));
    }
}
