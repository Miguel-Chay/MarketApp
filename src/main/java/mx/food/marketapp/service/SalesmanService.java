package mx.food.marketapp.service;

import java.util.List;
import java.util.Optional;

import java.util.LinkedList;

import org.springframework.transaction.annotation.Transactional;
// import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.food.marketapp.exception.BadRequestException;
import mx.food.marketapp.exception.NotFoundException;
import mx.food.marketapp.model.CityModel;
import mx.food.marketapp.model.SalesmanModel;
import mx.food.marketapp.model.SexModel;
import mx.food.marketapp.model.UserModel;
import mx.food.marketapp.model.request.RegisterSalesmanRequest;
import mx.food.marketapp.model.request.SalesmanRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import mx.food.marketapp.repository.SalesmanRepository;
import mx.food.marketapp.repository.UserRepository;

@Service
public class SalesmanService {

    @Autowired
    private SalesmanRepository salesmanRepository;
    @Autowired
    private UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
 


    @Transactional // Crear una transaccion
    public SalesmanModel registrar(RegisterSalesmanRequest request) {

        UserModel u = userRepository.findByUsername(request.getUsername());
        if (u!=null){
            throw new BadRequestException("El valor del campo username no esta disponible");
        }

        UserModel newuser = new UserModel();

        newuser.setUsername(request.getUsername());
        newuser.setPassword(passwordEncoder.encode(request.getPassword()));
        newuser.setEmail(request.getEmail());
        newuser.setType(request.getType());
        newuser = userRepository.save(newuser); 
              
        SalesmanModel newSalesman = new SalesmanModel();
        newSalesman.setUser_id(newuser.getId());
        newSalesman.setFirstname(request.getFirstname());
        newSalesman.setLastname(request.getLastname());
        newSalesman.setCellphone(request.getCellphone());
        newSalesman.setAddress(request.getAddress());
        try {          
            newSalesman.setCity(CityModel.valueOf(request.getCity()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para City" + ": " + request.getCity());
        }
        try {          
            newSalesman.setSex(SexModel.valueOf(request.getSex()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Sex" + ": " + request.getSex());
        }
 
        // newSalesman.setUser(newuser); // Relacionar 2 entidades
        newSalesman = salesmanRepository.save(newSalesman); 
        
        return newSalesman;
        
    } 


    @Transactional // Crear una transaccion
    public SalesmanModel actualizar(Integer id,SalesmanRequest request) {

        SalesmanModel salesman = salesmanRepository.findById(id).orElseThrow(()-> new NotFoundException());
         
    
        salesman.setFirstname(request.getFirstname());
        salesman.setLastname(request.getLastname());
        salesman.setCellphone(request.getCellphone());
        salesman.setAddress(request.getAddress());
        try {          
            salesman.setCity(CityModel.valueOf(request.getCity()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para City" + ": " + request.getCity());
        }
        try {          
            salesman.setSex(SexModel.valueOf(request.getSex()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Sex" + ": " + request.getSex());
        }
 
        // newSalesman.setUser(newuser); // Relacionar 2 entidades
        salesman = salesmanRepository.save(salesman); 
        
        return salesman;
    }

    @Transactional(readOnly = true)
    public List<SalesmanModel> getSalesmans() {
        List<SalesmanModel> salesmans = new LinkedList<>();
        salesmanRepository.findAll().iterator().forEachRemaining(salesmans::add);
        return salesmans;
    }

    @Transactional(readOnly = true)
    public SalesmanModel getById(Integer id) {

        Optional<SalesmanModel> opt = salesmanRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NotFoundException();
    }
    @Transactional()
    public void delete(Integer id) {
        try {
            salesmanRepository.deleteById(id);                        
        } catch (Exception e) {}
        
    }

 
}