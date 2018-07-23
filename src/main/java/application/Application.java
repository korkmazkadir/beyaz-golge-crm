package application;

import application.model.User;
import application.model.UserRole;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;


@SpringBootApplication
public class Application {

    @Autowired
    UserService userService;

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public User createRootUser() {
        return userService.saveUser(new User("root", "123", UserRole.ADMIN));
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

}
