package mx.food.marketapp.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.food.marketapp.model.CommerceModel;
import mx.food.marketapp.model.OrderDetailModel;
import mx.food.marketapp.model.ProductModel;
import mx.food.marketapp.model.request.CommerceRequest;
import mx.food.marketapp.service.CommerceService;

@RestController
@RequestMapping("/api")
public class CommerceRest {
  
  @Autowired
  private CommerceService commerceService;

  // registra un nuevo comercio
  @PostMapping("/commerces")
  public ResponseEntity<CommerceModel> create(@Valid @RequestBody CommerceRequest request) {
    CommerceModel commerce = commerceService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(commerce);
  }

  // actualiza un comercio
  @PutMapping("/commerces/{id}")
    public ResponseEntity<CommerceModel> update(@PathVariable Integer id, @Valid @RequestBody CommerceRequest request) {
      CommerceModel commerce = commerceService.update(id,request);
      return ResponseEntity.status(HttpStatus.CREATED).body(commerce);
  }

  // regresa todos los comercios 
  @GetMapping("/commerces")
  public ResponseEntity<List<CommerceModel>> obtenerSalesman() {
      List<CommerceModel> commerce = commerceService.getCommerces();
      return ResponseEntity.ok(commerce);
  }

  // regresa los productos de un determinado comercio 
  @GetMapping("/commerces/{id}/products")
  public ResponseEntity<List<ProductModel>> getCommerceProduct(@PathVariable Integer id) {
    List<ProductModel> products = commerceService.getProductByCommerce(id);
    return ResponseEntity.ok(products);
  }

  @GetMapping("/commerces/{id}")
  public ResponseEntity<CommerceModel> getCommerce(@PathVariable Integer id) {
    CommerceModel commerce = commerceService.getById(id);
    return ResponseEntity.status(HttpStatus.OK).body(commerce);
  }

  @DeleteMapping("/commerces/{id}")
  public ResponseEntity<Void> deleteCommerce(@PathVariable Integer id) {
    commerceService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // ver los pedidos de un negocio
  // regresa los productos de un determinado comercio 
  @GetMapping("/commerces/{id}/orders")
  public ResponseEntity<List<OrderDetailModel>> getOrdersByCommerce(@PathVariable Integer id) {
    List<OrderDetailModel> orders = commerceService.getOrdersByCommerce(id);
    return ResponseEntity.ok(orders);
  }
}
