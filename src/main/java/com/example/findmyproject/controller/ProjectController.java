package com.example.findmyproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.findmyproject.model.*;
import com.example.findmyproject.service.*;
import java.util.*;

@RestController
public class ProjectController {

    @Autowired
    private ProjectJpaService pjs;

    @GetMapping("/researchers")
    public ArrayList<Project> getProject() {
        return pjs.getProject();
    }

    @GetMapping("/researchers/{researcherId}")
    public Project getProjectById(@PathVariable("researcherId") int projectId) {
        return pjs.getProjectById(projectId);
    }

    @PostMapping("/researchers")
    public Project addProject(@RequestBody Project project) {
        return pjs.addProject(project);
    }

    @PutMapping("/researchers/{researcherId}")
    public Project updateProject(@PathVariable("researcherId") int projectId, @RequestBody Project project) {
        return pjs.updateProject(projectId, project);
    }

    @DeleteMapping("/researchers/{researcherId}")
    public void deleteProject(@PathVariable("researcherId") int projectId) {
        pjs.deleteProject(projectId);
    }

    @GetMapping("/researchers/{researcherId}/projects")
    public List<Researcher> getProjectResearcher(@PathVariable("researcherId") int projectId) {
        return pjs.getProjectResearcher(projectId);
    }
}
