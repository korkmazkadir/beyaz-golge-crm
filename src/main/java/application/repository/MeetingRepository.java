
package application.repository;

import application.model.Meeting;
import application.model.Registration;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface MeetingRepository extends CrudRepository<Meeting, Long>{
    
    @Override
    public List<Meeting> findAll();
    
    public List<Meeting> findByRegistrationOrderByDateDesc(Registration registration);
    
}
