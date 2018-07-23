package application.repository;

import application.model.Camp;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CampRepository extends CrudRepository<Camp, Long> {

    public List<Camp> findAll();
    
}
