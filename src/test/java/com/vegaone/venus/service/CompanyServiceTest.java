package com.vegaone.venus.service;

import com.vegaone.venus.dto.Company;
import com.vegaone.venus.repo.CompanyRepo;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
class CompanyServiceTest {

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

    @BeforeEach
    public void before() {
        companyRepo.deleteAll();
    }

    @Test
    void shouldGetCompany() {
        // given
        Company savedCompany = buildAndSaveCompany();

        // when
        Company fetchedCompany = companyService.getCompany(savedCompany.getId());

        // then
        Assertions.assertEquals(fetchedCompany, savedCompany);
    }

    @Test
    void createCompany() {
        Company companyToSave = buildCompany();

        // when
        Company savedCompany = companyService.createCompany(companyToSave);

        // then
        companyToSave.setId(savedCompany.getId()); // added the id so that the 2 classes can be compared properly
        Assertions.assertNotNull(savedCompany.getId());
        Assertions.assertEquals(companyToSave, savedCompany);
    }

    @Test
    void shouldUpdateCompany() {
        // given
        Company savedCompany = buildAndSaveCompany();
        savedCompany.setCountry("Chile");

        // when
        Company updatedCompany = companyService.updateCompany(savedCompany);

        // then
        Assertions.assertEquals(savedCompany, updatedCompany);
    }

    @Test
    void shouldDeleteCompany() {
        // given
        Company savedCompany = buildAndSaveCompany();

        // when
        companyService.deleteCompany(savedCompany.getId());

        // then
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class,
                () -> companyService.getCompany(savedCompany.getId()));
        Assertions.assertEquals("No company found with id=" + savedCompany.getId(), thrown.getMessage());
    }

    @Test
    void shouldGetAllCompanies() {
        // given
        Company companyOne = buildAndSaveCompany();
        Company companyTwo = buildAndSaveCompany();

        // when
        List<Company> allCompanies = companyService.getAllCompanies();

        // then
        Assertions.assertEquals(2, allCompanies.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoCompanyIsFound() {
        // when
        List<Company> allCompanies = companyService.getAllCompanies();

        // then
        Assertions.assertTrue(CollectionUtils.isEmpty(allCompanies));
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