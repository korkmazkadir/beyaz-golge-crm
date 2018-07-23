package application.repository;

import application.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findAll();

    public User findByUsernameAndPassword(String username, String password);

}
