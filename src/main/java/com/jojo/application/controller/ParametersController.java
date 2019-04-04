package com.jojo.application.controller;

import com.jojo.application.db.repository.ObjectRepository;
import com.jojo.application.db.repository.ParametersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ParametersController
{

    final private ParametersRepository repository;
    final private ObjectRepository objectRepository;

    public ParametersController(ParametersRepository repository, ObjectRepository objectRepository)
    {
        this.repository = repository;
        this.objectRepository = objectRepository;
    }

    @GetMapping("/parameters/{objectId}")
    public Map<Long, String> loadParametersMap(@PathVariable Long objectId)
    {
        return objectRepository.getOne(objectId).getParametersMap();
    }

    @PutMapping("/parameters/{objectId}")
    public void updateParametersMap(@PathVariable Long objectId,
                                    @RequestBody Map<Long,String> parametersMap)
    {
        objectRepository.getOne(objectId).updateParametersMap(parametersMap);
    }
}
