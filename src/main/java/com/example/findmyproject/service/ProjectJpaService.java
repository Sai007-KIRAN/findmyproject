package com.example.findmyproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.findmyproject.model.*;
import com.example.findmyproject.repository.*;
import java.util.*;

@Service
public class ProjectJpaService implements ProjectRepository {

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Autowired
    private ResearcherJpaRepository researcherJpaRepository;

    @Override
    public ArrayList<Project> getProject() {
        List<Project> list = projectJpaRepository.findAll();
        ArrayList<Project> all = new ArrayList<>(list);
        return all;
    }

    @Override
    public Project getProjectById(int projectId) {
        try {
            Project project = projectJpaRepository.findById(projectId).get();
            return project;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Project addProject(Project project) {
        List<Integer> researchersIds = new ArrayList<>();
        for (Researcher research : project.getResearchers()) {
            researchersIds.add(research.getResearcherId());
        }
        List<Researcher> researches = researcherJpaRepository.findAllById(researchersIds);
        if (researches.size() != researchersIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        project.setResearchers(researches);
        return projectJpaRepository.save(project);
    }

    @Override
    public Project updateProject(int projectId, Project project) {
        try {
            Project newProject = projectJpaRepository.findById(projectId).get();
            if (project.getProjectName() != null) {
                newProject.setProjectName(project.getProjectName());
            }
            if (project.getBudget() >= 0.0) {
                newProject.setBudget(project.getBudget());
            }
            if (project.getResearchers() != null) {
                List<Integer> projectsId = new ArrayList<>();
                for (Researcher research : project.getResearchers()) {
                    projectsId.add(research.getResearcherId());
                }
                List<Researcher> researcher = researcherJpaRepository.findAllById(projectsId);
                if (researcher.size() != projectsId.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                newProject.setResearchers(researcher);
            }
            return projectJpaRepository.save(newProject);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProject(int projectId) {
        try {
            projectJpaRepository.deleteById(projectId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Researcher> getProjectResearcher(int projectId) {
        try {
            Project project = projectJpaRepository.findById(projectId).get();
            return project.getResearchers();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}