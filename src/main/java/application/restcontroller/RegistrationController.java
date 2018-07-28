/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.restcontroller;

import application.model.Registration;
import application.repository.RegistrationRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    
    @Autowired
    RegistrationRepository registrationRepo;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Registration> getRegistrations(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Registration registration = registrationRepo.findById(id).get();
            List<Registration> registrations = new LinkedList<>();
            if (registration != null) {
                try {
                    registrations.add(registration);
                } catch (NoSuchElementException e) {
                    System.out.println("No such a registration in db with id : " + id);
                }
                return registrations;
            }
            return registrations;
        }

        return registrationRepo.findAll();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Registration saveRegistration(@RequestBody Registration registration) {

        System.out.println("Saving registration --> " + registration.toString());
        
        return registrationRepo.save(registration);
    }
    
}
