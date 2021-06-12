package mx.food.marketapp.rest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import mx.food.marketapp.model.AddressModel;
import mx.food.marketapp.model.request.AddressRequest;
import mx.food.marketapp.service.AddressService;

@RestController
@RequestMapping("/api")
public class AddressRest {
// PUT, GET, GET by ID, DELETE para entidad Address
    @Autowired
    private AddressService addressService;

    @PostMapping("/addresses")
    public ResponseEntity<AddressModel> registrarSalesman(@Valid @RequestBody AddressRequest request) {
        AddressModel u = addressService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<AddressModel> actualizarAddress(@PathVariable Integer id, @Valid @RequestBody AddressRequest request) {
        AddressModel u = addressService.actualizar(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressModel>> obtenerAddress() {
        List<AddressModel> addresss = addressService.getAddresss();
        return ResponseEntity.ok(addresss);
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressModel> getAddress(@PathVariable Integer id) {
        AddressModel u = addressService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/quienSoy")
    // public ResponseEntity<Address> getLoggedAddress(){
    //     Address address = (Address) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     return ResponseEntity.ok(address); 
    // }

    // @PostMapping("/register")
    // public ResponseEntity<Address> registrarAddress(@RequestBody AddressRequest request) {
    //     Address u = addressService.crear(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(u);
    // }

    
    

}