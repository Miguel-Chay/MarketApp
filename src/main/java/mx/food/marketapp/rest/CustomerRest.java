package mx.food.marketapp.rest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.food.marketapp.model.CustomerModel;
import mx.food.marketapp.model.request.RegisterCustomerRequest;
import mx.food.marketapp.model.request.CustomerRequest;
import mx.food.marketapp.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRest {
// PUT, GET, GET by ID, DELETE para entidad Customer
    @Autowired
    private CustomerService customerService;


    @PostMapping("/customers/register")
    public ResponseEntity<CustomerModel> registrarCustomer(@Valid @RequestBody RegisterCustomerRequest request) {
        CustomerModel u = customerService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerModel> actualizarCustomer(@PathVariable Integer id, @Valid @RequestBody CustomerRequest request) {
        CustomerModel u = customerService.actualizar(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerModel>> obtenerCustomer() {
        List<CustomerModel> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerModel> getCustomer(@PathVariable Integer id) {
        CustomerModel u = customerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    // @DeleteMapping("/customers/{id}")
    // public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
    //     customerService.delete(id);
    //     return ResponseEntity.noContent().build();
    // }

    // @GetMapping("/quienSoy")
    // public ResponseEntity<Customer> getLoggedCustomer(){
    //     Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     return ResponseEntity.ok(customer); 
    // }

    // @PostMapping("/register")
    // public ResponseEntity<Customer> registrarCustomer(@RequestBody CustomerRequest request) {
    //     Customer u = customerService.crear(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(u);
    // }

    
    

}