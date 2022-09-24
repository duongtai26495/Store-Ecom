package com.duongtai.estore.configs;

import com.duongtai.estore.entities.Role;
import com.duongtai.estore.entities.User;
import com.duongtai.estore.services.impl.RoleServiceImpl;
import com.duongtai.estore.services.impl.UserServiceImpl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(RoleServiceImpl roleService, UserServiceImpl userService){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Role role_user = new Role(Snippets.ROLE_USER);
                Role role_admin = new Role(Snippets.ROLE_ADMIN);
                roleService.saveNewRole(role_user);
                roleService.saveNewRole(role_admin);
                
            }
        };
    }
}
