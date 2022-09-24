package com.duongtai.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duongtai.estore.entities.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{

}
