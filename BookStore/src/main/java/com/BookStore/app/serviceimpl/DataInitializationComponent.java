package com.BookStore.app.serviceimpl;

import com.BookStore.app.model.User;
import com.BookStore.app.repository.UserRepository;
import com.BookStore.app.service.CartService;
import com.BookStore.app.user.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;

    private CartService cartService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializationComponent(UserRepository userRepository,
                                       PasswordEncoder passwordEncoder,
                                       CartService cartService) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.cartService=cartService;
    }

    @Override
    public void run(String... args) {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        String adminUsername = "BookStore@gmail.com";

        if (userRepository.findByEmail(adminUsername)==null) {
            User adminUser = new User();

            adminUser.setPassword(passwordEncoder.encode("BookStore"));
            adminUser.setFirstName("Book");
            adminUser.setLastName("Store");
            adminUser.setEmail(adminUsername);
            adminUser.setRole(UserRole.ROLE_ADMIN.toString());

            User admin=userRepository.save(adminUser);

            cartService.createCart(admin);
        }
    }

}
