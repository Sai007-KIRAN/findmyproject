package com.example.findmyproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.findmyproject.model.*;
import com.example.findmyproject.service.*;
import java.util.*;

@RestController
public class ResearcherController {

    @Autowired
    private ResearcherJpaService rjs;

    @GetMapping("/researchers/projects")
    public ArrayList<Researcher> getResearcher() {
        return rjs.getResearcher();
    }

    @GetMapping("/researchers/projects/{projectId}")
    public Researcher getResearcherById(@PathVariable("projectId") int researcherId) {
        return rjs.getResearcherById(researcherId);
    }

    @PostMapping("/researchers/projects")
    public Researcher addResearcher(@RequestBody Researcher researcher) {
        return rjs.addResearcher(researcher);
    }

    @PutMapping("/researchers/projects/{projectId}")
    public Researcher updateResearcher(@PathVariable("projectId") int researcherId,
            @RequestBody Researcher researcher) {
        return rjs.updateResearcher(researcherId, researcher);
    }

    @DeleteMapping("/researchers/projects/{projectId}")
    public void deleteResearcher(@PathVariable("projectId") int researcherId) {
        rjs.deleteResearcher(researcherId);
    }

    @GetMapping("/projects/{projectId}/researchers")
    public List<Project> getResearcherProject(@PathVariable("projectId") int researcherId) {
        return rjs.getResearcherProject(researcherId);
    }
}