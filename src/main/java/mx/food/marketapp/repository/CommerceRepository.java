package mx.food.marketapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.food.marketapp.model.CommerceModel;
@Repository
public interface CommerceRepository  extends JpaRepository< CommerceModel , Integer>  {
  
}
