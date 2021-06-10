package mx.food.marketapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.food.marketapp.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    public UserModel findByUsername(String username);
    // public Usuario findByToken(String token);

    // public User findByUserContaining(String nombre); // LIKE %nombre%

}