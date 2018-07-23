package application.restcontroller;

import application.model.Camp;
import application.repository.CampRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camp")
public class CampController {

    @Autowired
    CampRepository campRepo;

    @RequestMapping(method = RequestMethod.GET)
    public List<Camp> getCamps(@RequestParam(value = "id", required = false) Long id) {

        if (id != null) {
            Optional<Camp>  c = campRepo.findById(id);
            List<Camp> campList = new LinkedList<>();
            if(c != null){
                try {
                    campList.add(c.get());
                } catch (NoSuchElementException e) {
                    System.out.println("No such a camp in db with id : " + id);
                }
                return campList;
            }
            return campList;
        }

        return campRepo.findAll();
    }

}
