package com.vegaone.venus.controller;

import com.vegaone.venus.dto.Company;
import com.vegaone.venus.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("{id}")
    public Company getCompany(@PathVariable Long id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping()
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @PutMapping()
    public Company updateCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @DeleteMapping("{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}
