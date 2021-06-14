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
import mx.food.marketapp.model.OrderModel;
import mx.food.marketapp.model.request.OrderRequest;
import mx.food.marketapp.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderRest {
// PUT, GET, GET by ID, DELETE para entidad Order
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderModel> registrarSalesman(@Valid @RequestBody OrderRequest request) {
        OrderModel u = orderService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderModel> actualizarOrder(@PathVariable Integer id, @Valid @RequestBody OrderRequest request) {
        OrderModel u = orderService.actualizar(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderModel>> obtenerOrder() {
        List<OrderModel> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderModel> getOrder(@PathVariable Integer id) {
        OrderModel u = orderService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
   
    

}