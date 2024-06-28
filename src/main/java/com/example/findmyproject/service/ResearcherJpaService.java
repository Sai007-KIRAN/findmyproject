package com.example.findmyproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.findmyproject.model.*;
import com.example.findmyproject.repository.*;
import java.util.*;

@Service
public class ResearcherJpaService implements ResearcherRepository {

    @Autowired
    private ResearcherJpaRepository rjr;

    @Autowired
    private ProjectJpaRepository pjr;

    public ArrayList<Researcher> getResearcher() {
        List<Researcher> list = rjr.findAll();
        ArrayList<Researcher> all = new ArrayList<>(list);
        return all;
    }

    public Researcher getResearcherById(int researcherId) {
        try {
            Researcher researcher = rjr.findById(researcherId).get();
            return researcher;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Researcher addResearcher(Researcher researcher) {
        List<Integer> projectIds = new ArrayList<>();
        for (Project project : researcher.getProjects()) {
            projectIds.add(project.getProjectId());
        }
        List<Project> projects = pjr.findAllById(projectIds);
        researcher.setProjects(projects);

        for (Project project : projects) {
            project.getResearchers().add(researcher);
        }
        Researcher research = rjr.save(researcher);
        pjr.saveAll(projects);
        return research;
    }

    public Researcher updateResearcher(int researcherId, Researcher researcher) {
        try {
            Researcher newResearch = rjr.findById(researcherId).get();

            if (researcher.getResearcherName() != null) {
                newResearch.setResearcherName(researcher.getResearcherName());
            }
            if (researcher.getSpecialization() != null) {
                newResearch.setSpecialization(researcher.getSpecialization());
            }
            if (researcher.getProjects() != null) {
                List<Project> projects = newResearch.getProjects();
                for (Project project : projects) {
                    project.getResearchers().remove(newResearch);
                }
                pjr.saveAll(projects);
                List<Integer> newProjectsIds = new ArrayList<>();
                for (Project project : researcher.getProjects()) {
                    newProjectsIds.add(project.getProjectId());
                }
                List<Project> newProject = pjr.findAllById(newProjectsIds);
                for (Project project : newProject) {
                    project.getResearchers().add(newResearch);
                }
                pjr.saveAll(newProject);
                newResearch.setProjects(newProject);
            }
            return rjr.save(newResearch);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteResearcher(int researcherId) {
        try {
            Researcher research = rjr.findById(researcherId).get();
            List<Project> project = research.getProjects();
            for (Project pro : project) {
                pro.getResearchers().remove(research);
            }
            pjr.saveAll(project);
            rjr.deleteById(researcherId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public List<Project> getResearcherProject(int researcherId) {
        try {
            Researcher research = rjr.findById(researcherId).get();
            return research.getProjects();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}