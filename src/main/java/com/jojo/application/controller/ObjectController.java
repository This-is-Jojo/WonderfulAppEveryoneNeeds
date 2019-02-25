package com.jojo.application.controller;

import com.jojo.application.db.entity.Object;
import com.jojo.application.db.repository.ObjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ObjectController
{
    final private ObjectRepository objects;

    public ObjectController(ObjectRepository repository)
    {
        this.objects = repository;
    }

    @GetMapping("/objects")
    public List<Object> getObjects()
    {
        return objects.findAll();
    }

    @GetMapping("/objects/{objectId}")
    public Object getObject(@PathVariable Long objectId)
    {
        return objects.getOne(objectId);
    }

    @GetMapping("/objects/parentId:{parentId}")
    public Collection<Object> getChildren(@PathVariable Long parentId)
    {
        return objects.getObjectsByParentId(parentId);
    }

    @PostMapping("/objects")
    public Object createObject(@RequestBody Object object)
    {
        return objects.save(object);
    }

    @PutMapping("/objects/{objectId}")
    public Object updateObject(@PathVariable Long objectId,
                               @RequestBody Object objectRequest)
    {
        return objects.findById(objectId).map(object -> {
            object.setName(objectRequest.getName());
            object.setParentId(objectRequest.getParentId());
            return objects.save(object);
        }).orElseThrow(() -> new RuntimeException("Cannot update object with objectId = " + objectId));
    }

    @DeleteMapping("/objects/{objectId}")
    public ResponseEntity deleteObject(@PathVariable Long objectId)
    {
        return objects.findById(objectId).map(object -> {
            objects.delete(object);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Cannot delete object with objectId = " + objectId));
    }

}

