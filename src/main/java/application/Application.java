package application;

import application.model.Athlete;
import application.model.Camp;
import application.model.Registration;
import application.model.User;
import application.model.UserRole;
import application.repository.AthleteRepository;
import application.repository.CampRepository;
import application.repository.RegistrationRepository;
import application.restcontroller.AthleteController;
import application.restcontroller.CampController;
import application.service.UserService;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
    
    @Autowired
    public void registerCampAthlete(CampRepository campRepository, AthleteRepository athleteRepository){
        campRepository.save(new Camp("NBA Basketball Okulları 1. Hafta", "izmir", new Date(new java.util.Date().getTime()),new Date(new java.util.Date().getTime())));
        Athlete athlete =new Athlete();
        athlete.setIdNumber("45550666412");
        athlete.setName("Kadir");
        athlete.setSurname("Korkmaz");
        athleteRepository.save(athlete);
        
    }

}
