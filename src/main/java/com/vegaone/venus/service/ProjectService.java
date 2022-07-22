package com.vegaone.venus.service;

import com.vegaone.venus.domain.ProjectEntity;
import com.vegaone.venus.dto.Project;
import com.vegaone.venus.repo.ProjectRepo;
import com.vegaone.venus.util.MapperUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final MapperUtil mapperUtil;

    public ProjectService(ProjectRepo projectRepo, MapperUtil mapperUtil) {
        this.projectRepo = projectRepo;
        this.mapperUtil = mapperUtil;
    }

    public Project getProject(Long id) {
        ProjectEntity projectEntity = projectRepo
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No project found with id=" + id));

        return mapperUtil.map(projectEntity, Project.class);
    }

    public Project createProject(Project project) {
        ProjectEntity savedProject = projectRepo.save(mapperUtil.map(project, ProjectEntity.class));

        return mapperUtil.map(savedProject, Project.class);
    }

    public Project updateProject(Project project) {
        ProjectEntity savedProject = projectRepo.save(mapperUtil.map(project, ProjectEntity.class));
        return mapperUtil.map(savedProject, Project.class);
    }

    public void deleteProject(Long id) {
        if (projectRepo.existsById(id)) {
            projectRepo.deleteById(id);
        }
    }

    public List<Project> getAllProjectsForCompanyId(Long companyId){
        List<ProjectEntity> projectEntityList = projectRepo.findAllByCompanyId(companyId);
        return mapperUtil.mapList(projectEntityList, Project.class);
    }
}
