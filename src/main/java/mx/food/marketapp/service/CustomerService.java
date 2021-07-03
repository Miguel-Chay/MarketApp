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
// import mx.food.marketapp.model.CityModel;
import mx.food.marketapp.model.CustomerModel;
import mx.food.marketapp.model.SexModel;
import mx.food.marketapp.model.UserModel;
import mx.food.marketapp.model.PaymentModel;
import mx.food.marketapp.model.request.RegisterCustomerRequest;
import mx.food.marketapp.model.request.CustomerRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import mx.food.marketapp.repository.CustomerRepository;
import mx.food.marketapp.repository.UserRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
 


    @Transactional // Crear una transaccion
    public CustomerModel registrar(RegisterCustomerRequest request) {

        UserModel u = userRepository.findByUsername(request.getUsername());
        if (u!=null){
            throw new BadRequestException("El valor del campo username no esta disponible");
        }

        UserModel newuser = new UserModel();

        newuser.setUsername(request.getUsername());
        newuser.setPassword(passwordEncoder.encode(request.getPassword()));
        newuser.setEmail(request.getEmail());
        newuser.setType("customer");
        newuser = userRepository.save(newuser); 
              
        CustomerModel newCustomer = new CustomerModel();
        newCustomer.setUser_id(newuser.getId());
        newCustomer.setFirstname(request.getFirstname());
        newCustomer.setLastname(request.getLastname());
        newCustomer.setCellphone(request.getCellphone());
        
        try {          
            newCustomer.setSex(SexModel.valueOf(request.getSex()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Sex" + ": " + request.getSex());
        }
        try {          
            newCustomer.setPayment(PaymentModel.valueOf(request.getPayment()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para paymentType" + ": " + request.getPayment());
        }
        // newCustomer.setUser(newuser); // Relacionar 2 entidades
        newCustomer = customerRepository.save(newCustomer); 
        

        // ==================================================
        //                     CORREO
        // ==================================================


        return newCustomer;
        
    } 


    @Transactional // Crear una transaccion
    public CustomerModel actualizar(Integer id,CustomerRequest request) {

        CustomerModel customer = customerRepository.findById(id).orElseThrow(()-> new NotFoundException());
         
    
        customer.setFirstname(request.getFirstname());
        customer.setLastname(request.getLastname());
        customer.setCellphone(request.getCellphone());
        
        try {          
            customer.setSex(SexModel.valueOf(request.getSex()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Sex" + ": " + request.getSex());
        }
        try {          
            customer.setPayment(PaymentModel.valueOf(request.getPayment()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para paymentType" + ": " + request.getPayment());
        }
        // newCustomer.setUser(newuser); // Relacionar 2 entidades
        customer = customerRepository.save(customer); 
        
        return customer;
    }

    @Transactional(readOnly = true)
    public List<CustomerModel> getCustomers() {
        List<CustomerModel> customers = new LinkedList<>();
        customerRepository.findAll().iterator().forEachRemaining(customers::add);
        return customers;
    }

    @Transactional(readOnly = true)
    public CustomerModel getById(Integer id) {

        Optional<CustomerModel> opt = customerRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NotFoundException();
    }
    @Transactional()
    public void delete(Integer id) {
        try {
            customerRepository.deleteById(id);                        
        } catch (Exception e) {}
        
    }

 
}