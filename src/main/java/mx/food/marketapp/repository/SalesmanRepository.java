package mx.food.marketapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.food.marketapp.model.SalesmanModel;

@Repository
public interface SalesmanRepository extends JpaRepository<SalesmanModel, Integer> {

    public SalesmanModel findByFirstname(String username);
    // public Usuario findByToken(String token);

    // public Salesman findBySalesmanContaining(String nombre); // LIKE %nombre%

}