package com.jojo.application.controller;

import com.jojo.application.db.entity.Attribute;
import com.jojo.application.db.entity.Parameter;
import com.jojo.application.db.repository.AttributeRepository;
import com.jojo.application.db.repository.ObjectTypeRepository;
import com.jojo.application.db.repository.ParametersRepository;
import com.jojo.application.response.StringResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AttributesController
{
    final private AttributeRepository repository;
    final private ObjectTypeRepository objectTypeRepository;
    final private ParametersRepository parameters;

    public AttributesController(AttributeRepository repository, ObjectTypeRepository objectTypeRepository, ParametersRepository parameters)
    {
        this.repository = repository;
        this.objectTypeRepository = objectTypeRepository;
        this.parameters = parameters;
    }

    @GetMapping("/attributes/getNameOf={attrId}")
    public StringResponse getAttributeName(@PathVariable Long attrId)
    {
        return new StringResponse(repository.getOne(attrId).getName());
    }

    @GetMapping("/attributes/getAttributesMapForObjectType={objectTypeId}")
    public Map<Long, String> getAttributesMapForOt(@PathVariable Long objectTypeId)
    {
        return repository.getAttributesMapByObjectTypeId(objectTypeId);
    }

    @PostMapping("/attributes")
    public Attribute createAttribute(@RequestBody Attribute attribute)
    {
        return repository.save(attribute);
    }

    @PutMapping("/attributes/{attrId}")
    public Attribute updateAttribute(@PathVariable Long attrId,
                                   @RequestBody Attribute attributeRequest)
    {
        return repository.findById(attrId).map(attribute -> {
            attribute.setName(attributeRequest.getName());
            return repository.save(attribute);
        }).orElseThrow(() -> new RuntimeException("Cannot update attribute with attrId = " + attrId));
    }

    @GetMapping("/attributes/{objectTypeId}")
    public List<Attribute> getAttributes(@PathVariable Long objectTypeId)
    {
        return objectTypeRepository.getOne(objectTypeId).getAttributesList();
    }

    @DeleteMapping("/attributes/{attrId}")
    public ResponseEntity deleteAttribute(@PathVariable Long attrId)
    {
        List<Parameter> parametersByAttribute = parameters.getParametersByParametersPk_AttrId(attrId);
        parameters.deleteAll(parametersByAttribute);

        return repository.findById(attrId).map(attribute -> {
            repository.delete(attribute);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Cannot delete attribute with attrId = " + attrId));
    }


}
