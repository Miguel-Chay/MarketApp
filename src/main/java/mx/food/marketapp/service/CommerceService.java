package mx.food.marketapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import mx.food.marketapp.exception.BadRequestException;
import mx.food.marketapp.exception.NotFoundException;
import mx.food.marketapp.model.CityModel;
import mx.food.marketapp.model.CommerceModel;
import mx.food.marketapp.model.ProductModel;
import mx.food.marketapp.model.SalesmanModel;
import mx.food.marketapp.model.request.CommerceRequest;
import mx.food.marketapp.repository.CommerceRepository;
import mx.food.marketapp.repository.ProductRepository;
import mx.food.marketapp.repository.SalesmanRepository;

@Service
public class CommerceService {

  @Autowired
  private CommerceRepository commerceRepository;

  @Autowired
  private SalesmanRepository salesmanRepository;

  @Autowired
  private ProductRepository productRepository;
  @Transactional
  public CommerceModel create(CommerceRequest request) {
    SalesmanModel salesman = salesmanRepository.findById(request.getSalesmanId()).orElseThrow(()-> new NotFoundException("No se pudo encontrar al vendedor con id: "+request.getSalesmanId()));
    CommerceModel commerce = new CommerceModel();
    commerce.setSalesman(salesman);
    commerce.setCommercialName(request.getCommercialName());
    commerce.setRfc(request.getRfc());
    commerce.setDescription(request.getDescription());
    
    try {          
      commerce.setCity(CityModel.valueOf(request.getCity()));
    } catch (IllegalArgumentException e) {                   
        throw new BadRequestException("Valor invalido para City" + ": " + request.getCity());
    }

    commerce.setAddress(request.getAddress());
    commerce.setLogo(request.getLogo());
    commerce.setPhone(request.getPhone());
    commerce = commerceRepository.save(commerce);
    return commerce;
  }

  @Transactional
  public CommerceModel update(Integer id, CommerceRequest request) {
    CommerceModel commerce = commerceRepository.findById(id).orElseThrow(()-> new NotFoundException());
    SalesmanModel salesman = salesmanRepository.findById(request.getSalesmanId()).orElseThrow(()-> new NotFoundException());

    commerce.setSalesman(salesman);
    commerce.setCommercialName(request.getCommercialName());
    commerce.setRfc(request.getRfc());
    commerce.setDescription(request.getDescription());

    try {          
      commerce.setCity(CityModel.valueOf(request.getCity()));
    } catch (IllegalArgumentException e) {                   
        throw new BadRequestException("Valor invalido para City" + ": " + request.getCity());
    }

    commerce.setAddress(request.getAddress());
    commerce.setLogo(request.getLogo());
    commerce.setPhone(request.getPhone());
    commerce = commerceRepository.save(commerce);
    return commerce;
  }

  @Transactional(readOnly = true)
  public List<CommerceModel> getCommerces() {
    List<CommerceModel> commerce = new LinkedList<>();
    commerceRepository.findAll().iterator().forEachRemaining(commerce::add);
    return commerce;
  }

  @Transactional(readOnly = true)
  public CommerceModel getById(Integer id) {

    Optional<CommerceModel> commerceOpt = commerceRepository.findById(id);
    if (commerceOpt.isPresent()) {
      return commerceOpt.get();
    }
    throw new NotFoundException();
  }

  @Transactional()
  public void delete(Integer id) {
      try {
          commerceRepository.deleteById(id);                        
      } catch (Exception e) {}
      
  }

  @Transactional()
  public List<ProductModel> getProductByCommerce(Integer idCommerce) {
    CommerceModel commerce = commerceRepository.findById(idCommerce).orElseThrow(()-> new NotFoundException());;
    List<ProductModel> products = new LinkedList<>();
    productRepository.findByCommerce(commerce).iterator().forEachRemaining(products::add);
    System.out.println(products);
    return products;
  }

}
