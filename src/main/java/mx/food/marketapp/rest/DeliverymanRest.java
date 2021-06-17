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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.food.marketapp.model.DeliverymanModel;
import mx.food.marketapp.model.request.RegisterDeliverymanRequest;
import mx.food.marketapp.model.request.DeliverymanRequest;
import mx.food.marketapp.service.DeliverymanService;

@RestController
@RequestMapping("/api")
public class DeliverymanRest {
// PUT, GET, GET by ID, DELETE para entidad Deliveryman
    @Autowired
    private DeliverymanService deliverymanService;


    @PostMapping("/deliverymans/register")
    public ResponseEntity<DeliverymanModel> registrarDeliveryman(@Valid @RequestBody RegisterDeliverymanRequest request) {
        DeliverymanModel u = deliverymanService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PutMapping("/deliverymans/{id}")
    public ResponseEntity<DeliverymanModel> actualizarDeliveryman(@PathVariable Integer id, @Valid @RequestBody DeliverymanRequest request) {
        DeliverymanModel u = deliverymanService.actualizar(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/deliverymans")
    public ResponseEntity<List<DeliverymanModel>> obtenerDeliveryman() {
        List<DeliverymanModel> deliverymans = deliverymanService.getDeliverymans();
        return ResponseEntity.ok(deliverymans);
    }

    @GetMapping("/deliverymans/{id}")
    public ResponseEntity<DeliverymanModel> getDeliveryman(@PathVariable Integer id) {
        DeliverymanModel u = deliverymanService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    @DeleteMapping("/deliverymans/{id}")
    public ResponseEntity<Void> deleteDeliveryman(@PathVariable Integer id) {
        deliverymanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/quienSoy")
    // public ResponseEntity<Deliveryman> getLoggedDeliveryman(){
    //     Deliveryman deliveryman = (Deliveryman) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     return ResponseEntity.ok(deliveryman); 
    // }

    // @PostMapping("/register")
    // public ResponseEntity<Deliveryman> registrarDeliveryman(@RequestBody DeliverymanRequest request) {
    //     Deliveryman u = deliverymanService.crear(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(u);
    // }

    
    

}