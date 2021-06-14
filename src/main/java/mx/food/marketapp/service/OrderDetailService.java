package mx.food.marketapp.service;

import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import mx.food.marketapp.exception.*;
import org.springframework.stereotype.Service;
import mx.food.marketapp.model.OrderDetailKeyModel;
import mx.food.marketapp.model.OrderDetailModel;
import mx.food.marketapp.model.OrderModel;
import mx.food.marketapp.model.ProductModel;
import mx.food.marketapp.model.request.OrderDetailRequest;
import mx.food.marketapp.repository.OrderDetailRepository;
import mx.food.marketapp.repository.OrderRepository;
import mx.food.marketapp.repository.ProductRepository;

@Service
public class OrderDetailService {
    

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailModel crear(OrderDetailRequest request){
        OrderDetailModel orderDetail = new OrderDetailModel();

        OrderDetailKeyModel odKey = new OrderDetailKeyModel(); 
        
        OrderModel order = new OrderModel();
        ProductModel product = new ProductModel(); 
        
        order = orderRepository.findById(request.getOrderId()).orElseThrow(()-> new NotFoundException("No existe una orden con id: "+request.getOrderId()));
        product = productRepository.findById(request.getProductId()).orElseThrow(()-> new NotFoundException("No existe un producto con id: "+request.getProductId()));

        odKey.setOrderId(request.getOrderId());
        odKey.setProductId(request.getProductId());

        
        orderDetail.setId(odKey);
        // orderDetail.setOrder(order);
        // orderDetail.setProduct(product);
        orderDetail.setAmount(request.getAmount());
        orderDetail.setSubtotal(request.getAmount()*product.getPrice());
        orderDetail.setFinished(request.isFinished());
        orderDetail = orderDetailRepository.save(orderDetail);

        return orderDetail;    
    }


    public OrderDetailModel actualizar(Integer orderId,Integer productId, OrderDetailRequest request){

        OrderDetailModel orderDetail = getOrderDetail(orderId, productId);
        // OrderDetailKeyModel odKey = new OrderDetailKeyModel(); 
        
        

        // OrderModel order = orderRepository.findById(request.getOrderId()).orElseThrow(()-> new NotFoundException("No existe una orden con id: "+request.getOrderId()));
        ProductModel product = productRepository.findById(request.getProductId()).orElseThrow(()-> new NotFoundException("No existe un producto con id: "+request.getProductId()));

        // odKey.setOrderId(request.getOrderId());
        // odKey.setProductId(request.getProductId());
        
        // orderDetail.setId(odKey);
        // orderDetail.setOrder(order);
        // orderDetail.setProduct(product);
        orderDetail.setAmount(request.getAmount());
        orderDetail.setSubtotal(request.getAmount()*product.getPrice());
        orderDetail.setFinished(request.isFinished());
        orderDetail = orderDetailRepository.save(orderDetail);

        return orderDetail;
    }


    @Transactional
    public OrderDetailModel getOrderDetail(Integer orderId, Integer productId){

        OrderDetailKeyModel odKey = new OrderDetailKeyModel();

        // alumnoRepository.findById(idalumno).orElseThrow(() -> new NotFoundException("El alumno no se encuentra."));

        // profesorRepository.findById(idprofesor).orElseThrow(() -> new NotFoundException("El profesor no se encuentra."));
        // OrderModel order = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("No existe una orden con id: "+orderId));
        // ProductModel product = productRepository.findById(productId).orElseThrow(()-> new NotFoundException("No existe un producto con id: "+productId));

        odKey.setOrderId(orderId);
        odKey.setProductId(productId);
        // tutoriaLlave.setIdAlumno(idalumno);
        // tutoriaLlave.setIdProfesor(idprofesor);

        return orderDetailRepository.findById(odKey).orElseThrow(() -> new NotFoundException("El detalle de la orden no se encuentra."));
    }

    public List<OrderDetailModel> getOrderDetails(){
        System.out.println("============== 1 ===============");
        List<OrderDetailModel> oD = new LinkedList<>();
        System.out.println("============== 2 ===============");
        orderDetailRepository.findAll().iterator().forEachRemaining(oD::add);
        System.out.println("============== 3 ===============");
        return oD;
    }






}
