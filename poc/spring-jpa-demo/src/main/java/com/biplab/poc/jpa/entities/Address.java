package com.biplab.poc.jpa.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Biplab Nayak [Created on 6/25/2017].
 */

@Data
@Entity
@Table(name = "address")
public class Address {

    public enum AddressType { HOME, COMMERCIAL}

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "landmark")
    private String landMark;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "address_type")
    private AddressType addressType;

}
