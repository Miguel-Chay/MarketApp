package mx.food.marketapp.service;

import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import mx.food.marketapp.config.RabbitMqConfig;
import mx.food.marketapp.exception.*;
// import org.springframework.stereotype.Service;
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

    @Autowired
    private RabbitTemplate template;

    public OrderDetailModel crear(OrderDetailRequest request){
        OrderDetailModel orderDetail = new OrderDetailModel();

        OrderDetailKeyModel odKey = new OrderDetailKeyModel(); 
        
        OrderModel order = new OrderModel();
        ProductModel product = new ProductModel(); 
        
        order = orderRepository.findById(request.getOrderId()).orElseThrow(()-> new NotFoundException("No existe una orden con id: "+request.getOrderId()));
        product = productRepository.findById(request.getProductId()).orElseThrow(()-> new NotFoundException("No existe un producto con id: "+request.getProductId()));



        odKey.setOrderId(order);
        odKey.setProductId(product);


        orderDetail.setId(odKey);
        // orderDetail.setOrder(order);
        // orderDetail.setProduct(product);
        orderDetail.setAmount(request.getAmount());
        orderDetail.setSubtotal(request.getAmount()*product.getPrice());
        orderDetail.setFinished(request.isFinished());
        orderDetail = orderDetailRepository.save(orderDetail);

        // template.convertAndSend(RabbitMqConfig.EXCHANGE,"commerce"+"."+product.getCommerce().getId().toString(), orderDetail);
        return orderDetail;    
    }


    public OrderDetailModel actualizar(Integer orderId,Integer productId, OrderDetailRequest request){

        OrderDetailModel orderDetail = getOrderDetail(orderId, productId);
        
        ProductModel product = productRepository.findById(request.getProductId()).orElseThrow(()-> new NotFoundException("No existe un producto con id: "+request.getProductId()));

        
        orderDetail.setAmount(request.getAmount());
        orderDetail.setSubtotal(request.getAmount()*product.getPrice());
        orderDetail.setFinished(request.isFinished());
        orderDetail = orderDetailRepository.save(orderDetail);

        // agregamos a la cola 
        if(orderDetail.isFinished()) {
            template.convertAndSend(RabbitMqConfig.EXCHANGE,"commerce"+"."+product.getCommerce().getId().toString(), orderDetail);
        }
        return orderDetail;
    }


    @Transactional
    public OrderDetailModel getOrderDetail(Integer orderId, Integer productId){

        OrderDetailKeyModel odKey = new OrderDetailKeyModel();
        OrderModel order = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("El detalle de la orden no se encuentra. " ));
        ProductModel product = productRepository.findById(productId).orElseThrow(()-> new NotFoundException("El detalle de la orden no se encuentra. " ));
        odKey.setOrderId(order);
        odKey.setProductId(product);
        return orderDetailRepository.findById(odKey).orElseThrow(() -> new NotFoundException("El detalle de la orden no se encuentra."));
    }

    public List<OrderDetailModel> getOrderDetails(){
        List<OrderDetailModel> oD = new LinkedList<>();
        orderDetailRepository.findAll().iterator().forEachRemaining(oD::add);
        return oD;
    }

    public void deleteOrderDetails(Integer orderId, Integer productId){
        OrderDetailModel oD= this.getOrderDetail(orderId, productId);

        orderDetailRepository.deleteById(oD.getId());
    }

    // @RabbitListener(queues = "Commerce_queue")
    public OrderDetailModel receiveMessage(OrderDetailModel item) {
        Tut1Receiver receiver = new Tut1Receiver();
        receiver.receiveMessage(item);
        // System.out.println("Received Message from Items Queue >>"+item);
        return item;
    }


}
