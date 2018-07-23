package application.service;

import application.model.User;
import application.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }
    
    public User getUserByUsernameAndPassword(String username, String password){
        return userRepo.findByUsernameAndPassword(username, password);
    }

}
