package com.duongtai.estore.configs;

import com.duongtai.estore.entities.Image;
import com.duongtai.estore.entities.Role;
import com.duongtai.estore.entities.User;
import com.duongtai.estore.repositories.UserRepository;
import com.duongtai.estore.services.impl.RoleServiceImpl;
import com.duongtai.estore.services.impl.UserServiceImpl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(RoleServiceImpl roleService, UserRepository userRepository, UserServiceImpl userService, PasswordEncoder passwordEncoder){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Role role_user = new Role(Snippets.ROLE_USER);
                Role role_admin = new Role(Snippets.ROLE_ADMIN);
                roleService.saveNewRole(role_user);
                roleService.saveNewRole(role_admin);
                
                User user = new User();
                user.setFull_name("Master admin");
                user.setUsername("master9981");
                user.setEmail("master9981@gmail.com");
                user.setPassword(passwordEncoder.encode("Blackhat9981"));
                user.setGender(1);
                user.setProfile_image("default_admin.jpg");
                user.setRole(role_admin);
                user.setActive(true);
                if(userService.findByUsername(user.getUsername()) == null) {
                userRepository.save(user);
                }
            }
        };
    }
}
