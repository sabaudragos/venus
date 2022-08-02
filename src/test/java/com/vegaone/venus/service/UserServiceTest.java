package com.vegaone.venus.service;

import com.vegaone.venus.dto.Company;
import com.vegaone.venus.dto.Project;
import com.vegaone.venus.dto.User;
import com.vegaone.venus.repo.CompanyRepo;
import com.vegaone.venus.repo.ProjectRepo;
import com.vegaone.venus.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
public class UserServiceTest {

    private static final String FIRST_NAME = "John";
    private static final String NEW_FIRST_NAME = "Marty";
    private static final String LAT_NAME = "Smith";
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
    private CompanyRepo companyRepo;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    public void before() {
        userRepo.deleteAll();
        projectRepo.deleteAll();
        companyRepo.deleteAll();
    }

    @Test
    void createUser() {
        //given
        User user = buildUser();

        //when
        User savedUser = userService.createUser(user);

        //then
        user.setId(savedUser.getId());
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals(user, savedUser);
    }

    @Test
    void shouldGetUser() {
        //given
        User savedUser = buildAndSaveUser(null, null);

        //when
        User fetchedUser = userService.getUser(savedUser.getId());

        //then
        Assertions.assertEquals(fetchedUser, savedUser);

    }

    @Test
    void shouldUpdateUser() {
        //given
        User savedUser = buildAndSaveUser(null, null);
        savedUser.setFirstName(NEW_FIRST_NAME);

        //when
        User updatedUser = userService.updateUser(savedUser);

        //then
        Assertions.assertEquals(savedUser, updatedUser);
    }

    @Test
    void shouldDeleteUser() {
        //given
        User savedUsed = buildAndSaveUser(null, null);

        //when
        userService.deleteUser(savedUsed.getId());

        //then
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class,
                () -> userService.getUser(savedUsed.getId()));
        Assertions.assertEquals("No user found with id=" + savedUsed.getId(), thrown.getMessage());
    }

    @Test
    void shouldHaveCompanyAssigned() {
        //given
        Company company = buildAndSaveCompany();

        //when
        User user = buildAndSaveUser(company, null);

        //then
        Assertions.assertEquals(company, user.getCompany());
    }

    @Test
    void shouldHaveCompanyAndProjectAssigned() {
        //given
        Company company = buildAndSaveCompany();
        Project project = buildAndSaveProject(company);

        //then
        User user = buildAndSaveUser(company, project);

        //then
        Assertions.assertEquals(company, user.getCompany());
        Assertions.assertEquals(project, user.getProject());

    }

    @Test
    void shouldHaveTwoUsersAssignedToCompany() {
        //given
        Company company = buildAndSaveCompany();

        //when
        User firstUser = buildAndSaveUser(company, null);
        User secondUser = buildAndSaveUser(company, null);

        //then
        Assertions.assertEquals(2, userService.findAllByCompanyId(company.getId()).size());
    }

    @Test
    void shouldHaveTwoUsersAssignedToProject() {
        //given
        Company company = buildAndSaveCompany();
        Project firstProject = buildAndSaveProject(company);

        //when
        User firstUser = buildAndSaveUser(company, firstProject);
        User secondUsed = buildAndSaveUser(company, firstProject);

        //then
        Assertions.assertEquals(2, userService.findAllByProjectId(firstProject.getId()).size());
        Assertions.assertEquals(2, userService.findAllByCompanyIdAndProjectId(company.getId(), firstProject.getId()).size());
    }


    private User buildAndSaveUser(Company company, Project project) {
        User user = buildUser();

        if (company != null) {
            user.setCompany(company);
        }
        if (project != null) {
            user.setProject(project);
        }

        return userService.createUser(user);

    }

    private User buildUser() {
        User user = new User();

        user.setFirstName(FIRST_NAME);
        user.setLastName(LAT_NAME);

        return user;
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

        return project;
    }
}
