package mx.food.marketapp.service;

import java.util.List;
import java.util.Optional;

import java.util.LinkedList;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.food.marketapp.exception.*;
import mx.food.marketapp.model.ProductModel;
// import mx.food.marketapp.model.CityModel;
import mx.food.marketapp.model.CommerceModel;
// import mx.food.marketapp.model.CustomerModel;
import mx.food.marketapp.model.request.ProductRequest;

import mx.food.marketapp.repository.ProductRepository;
import mx.food.marketapp.repository.CommerceRepository;
// import mx.food.marketapp.repository.UserRepository;
// import mx.food.marketapp.repository.CustomerRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    // @Autowired
    // private CustomerRepository customerRepository;
    @Autowired
    private CommerceRepository commerceRepository;

    @Transactional // Crear una transaccion
    public ProductModel crear(ProductRequest request) {
        ProductModel product = new ProductModel();
        CommerceModel c = commerceRepository.findById(request.getCommerceId()).orElseThrow(()-> new NotFoundException("No existe el commercio con id:"+ request.getCommerceId()));               
        product.setCommerce(c);
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock()); 

        product = productRepository.save(product); 
        return product;
    }

    @Transactional // Crear una transaccion
    public ProductModel actualizar(Integer id,ProductRequest request) {

        ProductModel product = productRepository.findById(id).orElseThrow(()-> new NotFoundException("No existe el producto con id: "+ id));
        // ProductModel product = new ProductModel();
        CommerceModel c = commerceRepository.findById(request.getCommerceId()).orElseThrow(()-> new NotFoundException("No existe el commercio con id:"+ request.getCommerceId()));               
        product.setCommerce(c);
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock()); 
        
        product = productRepository.save(product); 
        return product;
        
    }

    @Transactional(readOnly = true)
    public List<ProductModel> getProducts() {
        List<ProductModel> products = new LinkedList<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }

    @Transactional(readOnly = true)
    public ProductModel getById(Integer id) {

        Optional<ProductModel> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NotFoundException();
    }

    @Transactional()
    public void delete(Integer id) {
        try {
            productRepository.deleteById(id);                        
        } catch (Exception e) {}
        
    }

 
}