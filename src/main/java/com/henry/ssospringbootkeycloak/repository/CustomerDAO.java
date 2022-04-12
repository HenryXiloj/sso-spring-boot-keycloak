package com.henry.ssospringbootkeycloak.repository;

import com.henry.ssospringbootkeycloak.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<Customer, Long> {

}
