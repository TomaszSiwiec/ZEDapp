package com.zedapp.companyservice.repository;

import com.zedapp.companyservice.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
    Company findCompanyByNip(String nip);
    List<Company> findCompaniesByName(String name);
    List<Company> findCompaniesByStreet(String street);
    List<Company> findCompaniesByZipCode(String zipCode);
    List<Company> findCompaniesByCity(String city);
    List<Company> findAllByOrderIdExists();
}
