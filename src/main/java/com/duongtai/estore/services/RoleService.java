package com.duongtai.estore.services;

import com.duongtai.estore.entities.ResponseObject;
import com.duongtai.estore.entities.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    ResponseEntity<ResponseObject> saveNewRole(Role role);
    Role getRoleByName(String name);
}
