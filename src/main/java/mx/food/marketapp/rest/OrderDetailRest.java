package mx.food.marketapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import mx.food.marketapp.model.OrderDetailModel;
import mx.food.marketapp.model.request.OrderDetailRequest;
import mx.food.marketapp.service.OrderDetailService;

public class OrderDetailRest {
    
    @Autowired
    private OrderDetailService orderDetailService;

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
    public ResponseEntity<OrderDetailModel> postOrderDetail(@RequestBody @Valid OrderDetailRequest request) throws URISyntaxException{
        OrderDetailModel oD = orderDetailService.crear(request);
        return ResponseEntity.created(new URI("/orderDetails/"+oD.getId())).body(oD);
    }

    // @PutMapping("/tutorias/{idalumno}/{idprofesor}")
    // public ResponseEntity<Tutoria> putTutorias(@PathVariable Integer idalumno, @PathVariable Integer idprofesor, @RequestBody TutoriaRequest request)
    //     throws URISyntaxException{
    //         Tutoria tutoria = tutoriaService.editarTutoria(idalumno,idprofesor,request);
    //         return ResponseEntity.ok().body(tutoria);
    // }

    // @DeleteMapping("/tutorias/{idalumno}/{idprofesor}")
    // public ResponseEntity<Void> eliminarTutoria(@PathVariable Integer idalumno, @PathVariable Integer idprofesor){
    //     tutoriaService.borrarTutoria(idalumno,idprofesor);
    //     return ResponseEntity.noContent().build();
    // }
}
