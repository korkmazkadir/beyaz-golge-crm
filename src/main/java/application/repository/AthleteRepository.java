package application.repository;

import application.model.Athlete;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AthleteRepository extends CrudRepository<Athlete, Long> {

    public List<Athlete> findAll();
    
}
