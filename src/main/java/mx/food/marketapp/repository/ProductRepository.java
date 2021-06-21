package mx.food.marketapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.food.marketapp.model.CommerceModel;
import mx.food.marketapp.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository< ProductModel,Integer> {
    public List<ProductModel> findByCommerce(CommerceModel commerce);
}
