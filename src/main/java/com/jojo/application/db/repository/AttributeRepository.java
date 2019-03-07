package com.jojo.application.db.repository;

import com.jojo.application.db.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public interface AttributeRepository extends JpaRepository<Attribute, Long>
{
    List<Attribute> getAttributesByObjectTypeId(Long objectTypeId);

    default Map<Long, String> getAttributesMapByObjectTypeId(Long objectTypeId)
    {
        return getAttributesByObjectTypeId(objectTypeId).stream().collect(Collectors.toMap(Attribute::getAttrId, Attribute::getName));
    }
}
