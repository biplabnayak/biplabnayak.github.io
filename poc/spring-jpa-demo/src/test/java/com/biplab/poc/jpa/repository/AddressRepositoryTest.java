package com.biplab.poc.jpa.repository;

import com.biplab.poc.jpa.AbstractContextInitializer;
import com.biplab.poc.jpa.entities.Address;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Biplab Nayak [Created on 6/26/2017].
 */

public class AddressRepositoryTest extends AbstractContextInitializer {

    @Autowired
    AddressRepository addressRepository;

    @Test
    public void saveAddressTest() {

        Address address = addressRepository.save(createSampleAddress());
        address.setCity("Mumbai");
        addressRepository.save(address);

    }

    public Address createSampleAddress() {
        Address address = new Address();
        address.setFullName("Biplab Nayak");
        return address;
    }
}
