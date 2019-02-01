package com.jojo.application.controller;

import com.jojo.application.db.components.ObjectIdGenerator;
import com.jojo.application.db.entity.Object;
import com.jojo.application.db.repository.ObjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class ObjectController
{
    final private ObjectRepository objects;
    final private ObjectIdGenerator objectIdGenerator;

    public ObjectController(ObjectRepository repository, ObjectIdGenerator objectIdGenerator)
    {
        this.objects = repository;
        this.objectIdGenerator = objectIdGenerator;
    }

    @GetMapping("/objects")
    public List<Object> getObjects()
    {
        return objects.findAll();
    }

    @GetMapping("/objects/{objectId}")
    public Object getObject(@PathVariable BigInteger objectId)
    {
        return objects.getOne(objectId);
    }

    @PostMapping("/objects/")
    public Object createObject(@RequestBody Object object)
    {
        return objects.save(object);
    }

    @PutMapping("/objects/{objectId}")
    public Object updateObject(@PathVariable BigInteger objectId,
                               @RequestBody Object objectRequest)
    {
        return objects.findById(objectId).map(object -> {
            object.setName(objectRequest.getName());
            object.setParent(objectRequest.getParent());
            return objects.save(object);
        }).orElseThrow(() -> new RuntimeException("Cannot update object with objectId = " + objectId));
    }

    @DeleteMapping("/objects/{objectId}")
    public ResponseEntity deleteObject(@PathVariable BigInteger objectId)
    {
        return objects.findById(objectId).map(object -> {
            objects.delete(object);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Cannot delete object with objectId = " + objectId));
    }

}
