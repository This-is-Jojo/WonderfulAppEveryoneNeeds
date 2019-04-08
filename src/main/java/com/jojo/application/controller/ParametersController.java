package com.jojo.application.controller;

import com.jojo.application.db.entity.Object;
import com.jojo.application.db.repository.ObjectRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ParametersController
{
    private static final Logger logger = LogManager.getLogger(ParametersController.class);

    final private ObjectRepository objectRepository;

    public ParametersController(ObjectRepository objectRepository)
    {
        this.objectRepository = objectRepository;
    }

    @GetMapping("/parameters/{objectId}")
    public Map<Long, String> loadParametersMap(@PathVariable Long objectId)
    {
        return objectRepository.getOne(objectId).getParametersMap();
    }

    @PutMapping("/parameters/{objectId}")
    public void updateParametersMap(@PathVariable Long objectId,
                                    @RequestBody List<List<String>> sourceMap)
    {
        logger.debug("Object:{} Incoming Map: {}", objectId, sourceMap.toString());
        Map<Long, String> parametersMap = new HashMap<>();
        sourceMap.forEach(entry -> parametersMap.put(Long.valueOf(entry.get(0)), entry.get(1)));
        logger.debug("Object:{} Result Map: {}", objectId, Arrays.toString(parametersMap.entrySet().toArray()));

        Object object = objectRepository.getOne(objectId);
        object.updateParametersMap(parametersMap);
        objectRepository.save(object);
    }
}
