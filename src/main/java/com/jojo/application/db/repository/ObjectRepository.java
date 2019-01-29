package com.jojo.application.db.repository;

import com.jojo.application.db.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ObjectRepository extends JpaRepository<Object, BigInteger>
{
}
