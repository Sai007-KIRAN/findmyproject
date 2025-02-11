package com.example.findmyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.findmyproject.model.Project;

@Repository
public interface ProjectJpaRepository extends JpaRepository<Project, Integer> {

}