package com.jojo.application.controller;

import com.jojo.application.db.repository.AttributeRepository;
import com.jojo.application.response.StringResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AttributesController
{
    final private AttributeRepository repository;

    public AttributesController(AttributeRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/attributes/getNameOf={attrId}")
    public StringResponse getAttributeName(@PathVariable Long attrId)
    {
        return new StringResponse(repository.getOne(attrId).getName());
    }

}
