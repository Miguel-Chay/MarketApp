    package mx.food.marketapp.repository;

    import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;


import mx.food.marketapp.model.OrderDetailKeyModel;
import mx.food.marketapp.model.OrderDetailModel;

    @Repository
    public interface OrderDetailRepository extends JpaRepository< OrderDetailModel,OrderDetailKeyModel> {
        
        // public OrderDetailModel findByOrderIdAndFinished(String orderId, boolean finished);
        public List <OrderDetailModel> findByOrderId(Integer order);

    
    }
