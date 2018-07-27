
package application.repository;

import application.model.Registration;
import application.model.RegistrationType;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface RegistrationRepository extends CrudRepository<Registration, Long>{
    
    public List<Registration> findAll();
    
    public List<Registration> findByRegistrationType( RegistrationType type);
    
}
