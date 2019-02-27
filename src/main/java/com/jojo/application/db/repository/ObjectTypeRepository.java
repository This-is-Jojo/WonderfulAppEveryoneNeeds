package com.jojo.application.db.repository;

import com.jojo.application.db.entity.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public interface ObjectTypeRepository extends JpaRepository<ObjectType, Long>
{
}
