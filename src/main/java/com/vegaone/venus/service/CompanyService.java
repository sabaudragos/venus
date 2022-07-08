package com.vegaone.venus.service;

import com.vegaone.venus.domain.CompanyEntity;
import com.vegaone.venus.dto.Company;
import com.vegaone.venus.repo.CompanyRepo;
import com.vegaone.venus.util.MapperUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepo companyRepo;

    private final MapperUtil mapperUtil;

    public CompanyService(CompanyRepo companyRepo, MapperUtil mapperUtil) {
        this.companyRepo = companyRepo;
        this.mapperUtil = mapperUtil;
    }

    public Company getCompany(Long id) {
        CompanyEntity companyEntity = companyRepo
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No company found with id=" + id));

        return mapperUtil.map(companyEntity, Company.class);
    }

    public List<Company> getAllCompanies() {
        List<CompanyEntity> companyEntityList = companyRepo.findAll();
        return mapperUtil.mapList(companyEntityList, Company.class);
    }

    public Company createCompany(Company company) {
        CompanyEntity savedCompany =
                companyRepo.save(
                        mapperUtil.map(company, CompanyEntity.class));
        return mapperUtil.map(savedCompany, Company.class);
    }

    public Company updateCompany(Company company) {
        CompanyEntity savedCompany =
                companyRepo.save(
                        mapperUtil.map(company, CompanyEntity.class));
        return mapperUtil.map(savedCompany, Company.class);
    }

    public void deleteCompany(Long id) {
        if (companyRepo.existsById(id)) {
            companyRepo.deleteById(id);
        }
    }
}
