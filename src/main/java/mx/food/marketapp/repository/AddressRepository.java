package mx.food.marketapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.food.marketapp.model.AddressModel;



@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {

    public AddressModel findByCustomerId(Integer customerid);
        // public Salesman findBySalesmanContaining(String nombre); // LIKE %nombre%

}