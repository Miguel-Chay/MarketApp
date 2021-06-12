package mx.food.marketapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.food.marketapp.exception.BadRequestException;
import mx.food.marketapp.exception.NotFoundException;
import mx.food.marketapp.model.CityModel;
import mx.food.marketapp.model.CommerceModel;
import mx.food.marketapp.model.SalesmanModel;
import mx.food.marketapp.model.request.CommerceRequest;
import mx.food.marketapp.repository.CommerceRepository;
import mx.food.marketapp.repository.SalesmanRepository;

@Service
public class CommerceService {

  @Autowired
  private CommerceRepository commerceRepository;

  @Autowired
  private SalesmanRepository salesmanRepository;

  @Transactional
  public CommerceModel create(CommerceRequest request) {
    SalesmanModel salesman = salesmanRepository.findById(request.getSalesmanId()).orElseThrow(()-> new NotFoundException());
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
}
