
package application.service;

import application.model.Athlete;
import application.repository.AthleteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {
    
    @Autowired
    AthleteRepository athleteRepository;
    
    public List<Athlete> getAllAthletes(){
        return athleteRepository.findAll();
    }
    
    public Athlete saveAthlete(Athlete athlete){
        return athleteRepository.save(athlete);
    }
}
