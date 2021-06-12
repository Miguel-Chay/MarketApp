package mx.food.marketapp.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.food.marketapp.model.CommerceModel;
import mx.food.marketapp.model.request.CommerceRequest;
import mx.food.marketapp.service.CommerceService;

@RestController
@RequestMapping("/api")
public class CommerceRest {
  
  @Autowired
  private CommerceService commerceService;

  @PostMapping("/commerces")
  public ResponseEntity<CommerceModel> registrarDeliveryman(@Valid @RequestBody CommerceRequest request) {
    CommerceModel commerce = commerceService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(commerce);
  }
}
