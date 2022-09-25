package com.duongtai.estore.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.duongtai.estore.configs.Snippets;
import com.duongtai.estore.entities.Vendor;
import com.duongtai.estore.repositories.VendorRepository;
import com.duongtai.estore.services.VendorService;
import static com.duongtai.estore.configs.MyUserDetail.getUsernameLogin;
public class VendorServiceImpl implements VendorService{

	@Autowired
	private VendorRepository vendorRepository;
	
	@Override
	public Vendor saveVendor(Vendor vendor) {
		if(!isExistByName(vendor.getName())) {
			Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
	        vendor.setCreated_at(sdf.format(date));
	        vendor.setLast_edited(sdf.format(date));
	        vendor.setCreated_by(getUsernameLogin());
	        if(vendorRepository.save(vendor) != null) {
	        	return vendor;
	        }	
		}
		return null;
		
	}

	@Override
	public Vendor editVendorById(Vendor vendor) {
		if(vendorRepository.existsById(vendor.getVendor_id())) {
			Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
			vendor.setLast_edited(sdf.format(date));
			if(vendorRepository.save(vendor) != null) {
				return vendor;
			}
		}
		return null;
	}

	@Override
	public void deleteVendorById(Long id) {
		if(vendorRepository.existsById(id)) {
			vendorRepository.deleteById(id);
		}
	}

	@Override
	public List<Vendor> findAllVendor() {
		return vendorRepository.findAll();
	}

	@Override
	public boolean isExistByName(String name) {
		List<Vendor> vendors = vendorRepository.findAll();
		for(Vendor vendor_row : vendors) {
			if(vendor_row.getName().toLowerCase().strip()
				.equals(name.toLowerCase().strip())) {
				return true;
			}
		}
		return false;
	}
}
