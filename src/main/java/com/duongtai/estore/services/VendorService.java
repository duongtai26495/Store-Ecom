package com.duongtai.estore.services;

import java.util.List;

import com.duongtai.estore.entities.Vendor;

public interface VendorService {
	
	Vendor saveVendor(Vendor vendor);
	
	Vendor editVendorById(Vendor vendor);
	
	void deleteVendorById(Long id);
	
	Vendor findVendorById(Long id);
	
	List<Vendor> findAllVendor();
	
	boolean isExistByName(String name);
}
