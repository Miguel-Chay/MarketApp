package mx.food.marketapp.rest;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
 
import mx.food.marketapp.model.OrderDetailModel;
import mx.food.marketapp.model.request.OrderDetailRequest;
import mx.food.marketapp.service.OrderDetailService;

@RestController
@RequestMapping("/api")
public class OrderDetailRest {
    
    @Autowired
    private OrderDetailService orderDetailService;

    // private RabbitTemplate template;
    
    @GetMapping("/orderDetails")
    public ResponseEntity<List<OrderDetailModel>> getOrderDetails(){
        List<OrderDetailModel> oD = orderDetailService.getOrderDetails();
        return ResponseEntity.ok(oD);
    }

    
    @GetMapping("/orderDetails/{orderId}/{productId}")
    public ResponseEntity<OrderDetailModel> getOrderDetail(@PathVariable Integer orderId, @PathVariable Integer productId){
        return ResponseEntity.ok().body(orderDetailService.getOrderDetail(orderId,productId));
    }


    @PostMapping("/orderDetails")
    public ResponseEntity<OrderDetailModel> postOrderDetail( @RequestBody @Valid OrderDetailRequest request) throws URISyntaxException{
        OrderDetailModel oD = orderDetailService.crear(request);
        return ResponseEntity.created(new URI("/orderDetails/"+oD.getId())).body(oD);
    }

    @PutMapping("/orderDetails/{orderId}/{productId}")
    public ResponseEntity<OrderDetailModel> putOrderDetail(@PathVariable Integer orderId, @PathVariable Integer productId, @RequestBody OrderDetailRequest request)
        throws URISyntaxException{
            OrderDetailModel oD = orderDetailService.actualizar(orderId,productId,request);
            return ResponseEntity.ok().body(oD);
    }

    @DeleteMapping("/orderDetails/{orderId}/{productId}")
    public ResponseEntity<Void> eliminarTutoria(@PathVariable Integer orderId, @PathVariable Integer productId){
        orderDetailService.deleteOrderDetails(orderId,productId);
        return ResponseEntity.noContent().build();
    }
}
