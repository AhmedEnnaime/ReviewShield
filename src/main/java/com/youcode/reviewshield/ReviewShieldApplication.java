package com.youcode.reviewshield;

import com.youcode.reviewshield.models.entities.Review;
import com.youcode.reviewshield.models.entities.Role;
import com.youcode.reviewshield.models.entities.User;
import com.youcode.reviewshield.models.entities.UserRole;
import com.youcode.reviewshield.repositories.ReviewRepository;
import com.youcode.reviewshield.repositories.RoleRepository;
import com.youcode.reviewshield.repositories.UserRepository;
import com.youcode.reviewshield.repositories.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
@AllArgsConstructor
public class ReviewShieldApplication {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private ReviewRepository reviewRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReviewShieldApplication.class, args);
    }

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return args -> {

            var role1 = Role.builder()
                    .id(UUID.randomUUID())
                    .name("ADMIN")
                    .build();
            var role2 = Role.builder()
                    .id(UUID.randomUUID())
                    .name("USER")
                    .build();
            roleRepository.saveAll(Arrays.asList(role1, role2));

            User user = User.builder()
                    .id(UUID.randomUUID())
                    .username("AhmedEnnaime")
                    .password(passwordEncoder.encode("password"))
                    .build();

            userRepository.save(user);

            UserRole userRole1 = UserRole.builder()
                            .id(UUID.randomUUID())
                            .user(user)
                            .role(role1)
                            .build();
            UserRole userRole2 = UserRole.builder()
                    .id(UUID.randomUUID())
                    .user(user)
                    .role(role2)
                    .build();
            userRoleRepository.saveAll(Arrays.asList(userRole1, userRole2));

            reviewRepository.save(
                    Review.builder()
                            .id(UUID.randomUUID())
                            .title("title")
                            .message("ach had service dyal walo")
                            .user(user)
                            .build()
            );

            System.out.println("Executing code during application startup.");
        };
    }

}
