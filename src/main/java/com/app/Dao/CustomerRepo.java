package com.app.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer>{

    public Customer findByName(String name);
}
