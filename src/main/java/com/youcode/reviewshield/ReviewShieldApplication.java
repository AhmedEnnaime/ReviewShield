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
            var role3 = Role.builder()
                    .id(UUID.randomUUID())
                    .name("MODERATOR")
                    .build();
            var role2 = Role.builder()
                    .id(UUID.randomUUID())
                    .name("USER")
                    .build();
            roleRepository.saveAll(Arrays.asList(role1, role2, role3));

            User user = User.builder()
                    .id(UUID.randomUUID())
                    .username("AhmedEnnaime")
                    .password(passwordEncoder.encode("password"))
                    .build();

            userRepository.save(user);

            UserRole userRole1 = UserRole.builder()
                            .id(UUID.randomUUID())
                            .user(user)
                            .role(role2)
                            .build();
            UserRole userRole2 = UserRole.builder()
                    .id(UUID.randomUUID())
                    .user(user)
                    .role(role3)
                    .build();
            userRoleRepository.saveAll(Arrays.asList(userRole1, userRole2));

            reviewRepository.saveAll(
                    Arrays.asList(Review.builder()
                            .id(UUID.randomUUID())
                            .title("Title 1")
                            .message("ach had service dyal walo")
                            .reactions(1)
                            .reported(false)
                            .user(user)
                            .build(),
                            Review.builder()
                            .id(UUID.randomUUID())
                            .title("title 2")
                            .reactions(1)
                            .reported(false)
                            .message("Montakhab hazo b 2-0")
                            .user(user)
                            .build())
            );

            System.out.println("Executing code during application startup.");
        };
    }

}
