
package application.service;

import application.model.Registration;
import application.model.RegistrationType;
import application.repository.RegistrationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    
    @Autowired
    RegistrationRepository registrationRepo;
    
    public List<Registration> getAllPreRegistrations(){
        List<Registration> regs = registrationRepo.findByRegistrationType(RegistrationType.PRE_REGISTRATION);
        System.out.println("Pre registration number is " + regs.size());
        return regs;
    }
    
}
