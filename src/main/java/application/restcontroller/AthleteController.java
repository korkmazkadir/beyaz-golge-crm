package application.restcontroller;

import application.model.Athlete;
import application.repository.AthleteRepository;
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
@RequestMapping("/athlete")
public class AthleteController {

    @Autowired
    AthleteRepository athleteRepo;

    @RequestMapping(method = RequestMethod.GET)
    public List<Athlete> getAthletes(@RequestParam(value = "id", required = false) String tcNumber) {
        if (tcNumber != null) {
            Athlete athlete = athleteRepo.findOneByIdNumber(tcNumber);
            List<Athlete> athleteList = new LinkedList<>();
            if (athlete != null) {
                try {
                    athleteList.add(athlete);
                } catch (NoSuchElementException e) {
                    System.out.println("No such a athlete in db with tc number : " + tcNumber);
                }
                return athleteList;
            }
            return athleteList;
        }

        return athleteRepo.findAll();
    }


    @RequestMapping(method = RequestMethod.POST)
    public Athlete saveAthlete(@RequestBody Athlete athlete) {

        System.out.println("Saving athlete --> " + athlete.toString());
        
        return athleteRepo.save(athlete);
    }
    
}
