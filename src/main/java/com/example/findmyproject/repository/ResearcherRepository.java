package com.example.findmyproject.repository;

import java.util.*;
import com.example.findmyproject.model.*;

public interface ResearcherRepository {
    ArrayList<Researcher> getResearcher();

    Researcher getResearcherById(int researcherId);

    Researcher addResearcher(Researcher researcher);

    Researcher updateResearcher(int researcherId, Researcher researcher);

    void deleteResearcher(int researcherId);

    List<Project> getResearcherProject(int researcherId);
}