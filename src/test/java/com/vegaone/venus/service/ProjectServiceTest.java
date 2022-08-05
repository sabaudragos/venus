package com.vegaone.venus.service;

import com.vegaone.venus.dto.Company;
import com.vegaone.venus.dto.Project;
import com.vegaone.venus.repo.CompanyRepo;
import com.vegaone.venus.repo.ProjectRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@SpringBootTest
public class ProjectServiceTest {

    private static final String PROJECT_NAME = "Dummy project";
    private static final String NEW_PROJECT_NAME = "New dummy project";
    private static final String PROJECT_DESCRIPTION = "Dummy project description";
    private static final String COMPANY_NAME = "Company Inc";
    private static final String EMAIL = "some@email.com";
    private static final String VAT_NUMBER = "some vat number";
    private static final String REGISTRATION_NUMBER = "some registration number";
    private static final String PHONE_NUMBER = "some phone number";
    private static final String ADDRESS = "some address";
    private static final String COUNTRY = "France";
    private static final String CITY = "Paris";

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @BeforeEach
    public void before() {
        projectRepo.deleteAll();
        companyRepo.deleteAll();
    }

    @Test
    void shouldGetProject() {
        //given
        Project savedProject = buildAndSaveProject(null);

        //when
        Project fetchedProject = projectService.getProject(savedProject.getId());

        //then
        Assertions.assertEquals(fetchedProject, savedProject);
    }

    @Test
    void createProject() {
        //given
        Project projectToSave = buildProject();

        //when
        Project savedProject = projectService.createProject(projectToSave);

        //then
        projectToSave.setId(savedProject.getId());
        Assertions.assertNotNull(savedProject.getId());
        Assertions.assertEquals(projectToSave, savedProject);
    }

    @Test
    void shouldUpdateProject() {
        //given
        Project savedProject = buildAndSaveProject(null);
        savedProject.setName(NEW_PROJECT_NAME);

        //when
        Project updatedProject = projectService.updateProject(savedProject);

        //then
        Assertions.assertEquals(savedProject, updatedProject);
    }

    @Test
    void shouldDeleteProject() {
        //given
        Project savedProject = buildAndSaveProject(null);

        //when
        projectService.deleteProject(savedProject.getId());

        //then
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class,
                () -> projectService.getProject(savedProject.getId()));
        Assertions.assertEquals("No project found with id=" + savedProject.getId(), thrown.getMessage());
    }

    @Test
    void shouldHaveCompanyAssigned() {
        //given
        Company company = buildAndSaveCompany();

        //when
        Project project = buildAndSaveProject(company);

        //then
        Assertions.assertEquals(company.getId(), project.getCompany().getId());
    }

    @Test
    void shouldDeleteProjectAndCompany() {
        //given
        Company company = buildAndSaveCompany();
        Project project = buildAndSaveProject(company);

        //when
        companyRepo.deleteById(company.getId());

        //then
        EntityNotFoundException thrownProject = Assertions.assertThrows(EntityNotFoundException.class,
                () -> projectService.getProject(project.getId()));
        Assertions.assertEquals("No project found with id=" + project.getId(), thrownProject.getMessage());
        EntityNotFoundException thrownCompany = Assertions.assertThrows(EntityNotFoundException.class,
                () -> companyService.getCompany(company.getId()));
        Assertions.assertEquals("No company found with id=" + company.getId(), thrownCompany.getMessage());
    }

    @Test
    void shouldHaveMultipleProjects() {
        //given
        Company company = buildAndSaveCompany();

        //when
        Project project = buildAndSaveProject(company);
        Project secondProject = buildAndSaveProject(company);

        //then
        Assertions.assertEquals(2, projectService.getAllProjectsForCompanyId(company.getId()).size());
    }

    private Project buildAndSaveProject(Company company) {
        Project project = buildProject();
        if (company != null) {
            project.setCompany(company);
        }

        return projectService.createProject(project);
    }

    private Project buildProject() {
        Project project = new Project();

        project.setName(PROJECT_NAME);
        project.setDescription(PROJECT_DESCRIPTION);
        project.setUsers(new ArrayList<>());

        return project;
    }

    private Company buildAndSaveCompany() {
        Company company = buildCompany();
        return companyService.createCompany(company);
    }

    private Company buildCompany() {
        Company company = new Company();

        company.setName(COMPANY_NAME);
        company.setEmail(EMAIL);
        company.setVatNumber(VAT_NUMBER);
        company.setRegistrationNumber(REGISTRATION_NUMBER);
        company.setPhone(PHONE_NUMBER);
        company.setAddress(ADDRESS);
        company.setCity(CITY);
        company.setCountry(COUNTRY);

        return company;
    }

}
