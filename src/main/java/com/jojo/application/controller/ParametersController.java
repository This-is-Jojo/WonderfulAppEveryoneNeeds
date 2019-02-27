package com.jojo.application.controller;

import com.jojo.application.db.repository.ParametersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ParametersController
{

    final private ParametersRepository repository;

    public ParametersController(ParametersRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/parameters/{objectId}")
    public Map<Long, String> loadParametersMap(@PathVariable Long objectId)
    {
        return repository.getParametersMapByObjectId(objectId);
    }

    @PutMapping("/parameters/{objectId}")
    public void updateParametersMap(@PathVariable Long objectId,
                                    @RequestBody Map<Long,String> parametersMap)
    {
        repository.updateParameters(objectId, parametersMap);
    }
}
