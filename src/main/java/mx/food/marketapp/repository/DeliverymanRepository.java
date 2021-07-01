package mx.food.marketapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.food.marketapp.model.DeliverymanModel;


@Repository
public interface DeliverymanRepository extends JpaRepository<DeliverymanModel, Integer> {

    public DeliverymanModel findByFirstname(String username);
        // public Salesman findBySalesmanContaining(String nombre); // LIKE %nombre%


}