package com.jojo.application.db.repository;

import com.jojo.application.db.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public interface ObjectRepository extends JpaRepository<Object, Long>
{
    List<Object> getObjectsByObjectTypeId(Long objectTypeId);

    List<Object> getObjectsByParentId(Long parentId);
}
