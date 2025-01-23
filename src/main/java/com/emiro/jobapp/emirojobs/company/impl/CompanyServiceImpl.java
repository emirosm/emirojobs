package com.emiro.jobapp.emirojobs.company.impl;

import com.emiro.jobapp.emirojobs.company.Company;
import com.emiro.jobapp.emirojobs.company.CompanyRepository;
import com.emiro.jobapp.emirojobs.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Optional<Company> updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            updatedCompany.setId(id);
            companyRepository.save(updatedCompany);
            return companyOptional;
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCompany(Long id) {
        companyRepository.deleteById(id);
        return companyRepository.existsById(id);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }


}
