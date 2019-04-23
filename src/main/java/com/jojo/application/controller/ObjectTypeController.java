package com.jojo.application.controller;

import com.jojo.application.db.entity.ObjectType;
import com.jojo.application.db.repository.ObjectTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/objectTypes")
    public List<ObjectType> getObjectTypes()
    {
        return this.objectTypes.findAll();
    }

    @PostMapping("/objectTypes")
    public Object createObjectType(@RequestBody ObjectType objectType)
    {
        return objectTypes.save(objectType);
    }

    @PutMapping("/objectTypes/{objectTypeId}")
    public Object updateObjectType(@PathVariable Long objectTypeId,
                               @RequestBody ObjectType objectRequest)
    {
        return objectTypes.findById(objectTypeId).map(objectType -> {
            objectType.setName(objectRequest.getName());
            return objectTypes.save(objectType);
        }).orElseThrow(() -> new RuntimeException("Cannot update objectType with objectTypeId = " + objectTypeId));
    }

    @DeleteMapping("/objectTypes/{objectTypeId}")
    public ResponseEntity deleteAttribute(@PathVariable Long objectTypeId)
    {
        return objectTypes.findById(objectTypeId).map(objectType -> {
            objectTypes.delete(objectType);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Cannot delete objectType with objectTypeId = " + objectTypeId));
    }
}
