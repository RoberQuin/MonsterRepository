package com.example.mosntruosapi.repository;

import com.example.mosntruosapi.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

}
