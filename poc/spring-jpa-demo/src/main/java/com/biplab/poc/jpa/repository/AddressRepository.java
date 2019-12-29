package com.biplab.poc.jpa.repository;

import com.biplab.poc.jpa.entities.Address;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Biplab Nayak [Created on 6/25/2017].
 */
public interface AddressRepository extends CrudRepository<Address, Long> {
}
