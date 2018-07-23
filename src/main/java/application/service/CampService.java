package application.service;

import application.model.Camp;
import application.repository.CampRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampService {

    @Autowired
    CampRepository campRepo;

    public List<Camp> getAllCamps() {
        return campRepo.findAll();
    }

    public Camp saveCamp(Camp camp){
        return campRepo.save(camp);
    }
    
}
