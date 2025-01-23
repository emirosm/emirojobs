package com.emiro.jobapp.emirojobs.company;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CompanyService {
    List<Company> getCompanies();
    Optional<Company> getCompanyById(Long id);
    Optional<Company> updateCompany(Long id, Company company);
    boolean deleteCompany(Long id);
    void createCompany(Company company);
}
