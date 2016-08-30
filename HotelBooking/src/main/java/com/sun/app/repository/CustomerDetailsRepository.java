package com.sun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sun.app.bean.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Integer> {

}
