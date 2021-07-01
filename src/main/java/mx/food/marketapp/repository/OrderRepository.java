package mx.food.marketapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.food.marketapp.model.DeliverymanModel;
import mx.food.marketapp.model.OrderModel;
import mx.food.marketapp.model.OrderStatusModel;

@Repository
public interface OrderRepository  extends JpaRepository< OrderModel,Integer>{

    public List<OrderModel> findByDeliverymanAndStatus(DeliverymanModel deliverymanId,OrderStatusModel status);
    
}
