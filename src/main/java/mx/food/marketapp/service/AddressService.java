package mx.food.marketapp.service;

import java.util.List;
import java.util.Optional;

import java.util.LinkedList;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.food.marketapp.exception.*;
import mx.food.marketapp.model.AddressModel;
import mx.food.marketapp.model.CityModel;
import mx.food.marketapp.model.CustomerModel;
import mx.food.marketapp.model.request.AddressRequest;

import mx.food.marketapp.repository.AddressRepository;
import mx.food.marketapp.repository.UserRepository;
import mx.food.marketapp.repository.CustomerRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional // Crear una transaccion
    public AddressModel crear(AddressRequest request) {

        AddressModel address = new AddressModel();
        System.out.println("================1==============");
        CustomerModel c = customerRepository.findById(request.getCustomerId()).orElseThrow(()-> new NotFoundException("No existe el cliente con id:"+ request.getCustomerId()));               
        
        address.setCustomer(c);
        address.setCrossing(request.getCrossing());
        address.setStreet(request.getStreet());
        address.setSuburb(request.getSuburb());
        System.out.println("================2==============");

        try {          
            address.setCity(CityModel.valueOf(request.getCity()));
            System.out.println("================3==============");

        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para City" + ": " + request.getCity());
        }
        System.out.println("================4==============");

        address = addressRepository.save(address); 
        System.out.println("================5==============");

        return address;
    }

    @Transactional // Crear una transaccion
    public AddressModel actualizar(Integer id,AddressRequest request) {

        AddressModel address = addressRepository.findById(id).orElseThrow(()-> new NotFoundException("No existe la direccion con id:"+ id));
        
        CustomerModel c = customerRepository.findById(request.getCustomerId()).orElseThrow(()-> new NotFoundException("No existe el cliente con id:"+ request.getCustomerId()));               
        
        address.setCustomer(c);
        address.setCrossing(request.getCrossing());
        address.setStreet(request.getStreet());
        address.setSuburb(request.getSuburb());
        try {          
            address.setCity(CityModel.valueOf(request.getCity()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para City" + ": " + request.getCity());
        }
        address = addressRepository.save(address); 
        return address;
        
    }

    @Transactional(readOnly = true)
    public List<AddressModel> getAddresss() {
        List<AddressModel> addresss = new LinkedList<>();
        addressRepository.findAll().iterator().forEachRemaining(addresss::add);
        return addresss;
    }

    @Transactional(readOnly = true)
    public AddressModel getById(Integer id) {

        Optional<AddressModel> opt = addressRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NotFoundException();
    }

    @Transactional()
    public void delete(Integer id) {
        try {
            addressRepository.deleteById(id);                        
        } catch (Exception e) {}
        
    }

 
}