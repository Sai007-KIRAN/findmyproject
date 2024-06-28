package com.example.findmyproject.repository;

import java.util.*;
import com.example.findmyproject.model.*;

public interface ProjectRepository {
    ArrayList<Project> getProject();

    Project getProjectById(int projectId);

    Project addProject(Project project);

    Project updateProject(int projectId, Project project);

    void deleteProject(int projectId);

    List<Researcher> getProjectResearcher(int projectId);
}