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
import mx.food.marketapp.model.DeliverymanModel;
import mx.food.marketapp.model.OrderModel;
import mx.food.marketapp.model.request.RegisterDeliverymanRequest;
import mx.food.marketapp.model.request.DeliverymanRequest;
import mx.food.marketapp.service.DeliverymanService;

@RestController
@RequestMapping("/api")
public class DeliverymanRest {
// PUT, GET, GET by ID, DELETE para entidad Deliveryman
    @Autowired
    private DeliverymanService deliverymanService;


    @PostMapping("/deliverymen/register")
    public ResponseEntity<DeliverymanModel> registrarDeliveryman(@Valid @RequestBody RegisterDeliverymanRequest request) {
        DeliverymanModel u = deliverymanService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PutMapping("/deliverymen/{id}")
    public ResponseEntity<DeliverymanModel> actualizarDeliveryman(@PathVariable Integer id, @Valid @RequestBody DeliverymanRequest request) {
        DeliverymanModel u = deliverymanService.actualizar(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/deliverymen")
    public ResponseEntity<List<DeliverymanModel>> obtenerDeliveryman() {
        List<DeliverymanModel> deliverymans = deliverymanService.getDeliverymans();
        return ResponseEntity.ok(deliverymans);
    }

    @GetMapping("/deliverymen/{id}")
    public ResponseEntity<DeliverymanModel> getDeliveryman(@PathVariable Integer id) {
        DeliverymanModel u = deliverymanService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    // @DeleteMapping("/deliverymen/{id}")
    // public ResponseEntity<Void> deleteDeliveryman(@PathVariable Integer id) {
    //     deliverymanService.delete(id);
    //     return ResponseEntity.noContent().build();
    // }


    @GetMapping("/deliverymen/orders")
    public ResponseEntity<List<OrderModel>> getOrders(){
        List<OrderModel> orders = deliverymanService.getOrdersavailable();
        return ResponseEntity.ok(orders);
    }
    // deliverymen/orders/1/submit
    
    @PutMapping("/deliverymen/{deliverymanId}/orders/{orderId}/submit")
    public ResponseEntity<OrderModel> OrderSubmit(@PathVariable Integer deliverymanId,@PathVariable Integer orderId) {
        OrderModel u = deliverymanService.submit(deliverymanId,orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }
    
    @PutMapping("/deliverymen/{deliverymanId}/orders/{orderId}/delivered")
    public ResponseEntity<OrderModel> OrderDelivered(@PathVariable Integer deliverymanId,@PathVariable Integer orderId) {
        OrderModel u = deliverymanService.delivered(deliverymanId,orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

}