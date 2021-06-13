package mx.food.marketapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.food.marketapp.model.OrderModel;

@Repository
public interface OrderRepository  extends JpaRepository< OrderModel,Integer>{
  
}
