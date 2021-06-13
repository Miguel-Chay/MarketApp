package mx.food.marketapp.service;

import java.util.List;
import java.util.Optional;

import java.util.LinkedList;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.food.marketapp.exception.*;
import mx.food.marketapp.model.OrderModel;
import mx.food.marketapp.model.OrderStatusModel;
import mx.food.marketapp.model.PaymentModel;
// import mx.food.marketapp.model.CityModel;
import mx.food.marketapp.model.CustomerModel;
import mx.food.marketapp.model.DeliverymanModel;
import mx.food.marketapp.model.request.OrderRequest;

import mx.food.marketapp.repository.OrderRepository;
// import mx.food.marketapp.repository.UserRepository;
import mx.food.marketapp.repository.CustomerRepository;
import mx.food.marketapp.repository.DeliverymanRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DeliverymanRepository deliverymanRepository;

    @Transactional // Crear una transaccion
    public OrderModel crear(OrderRequest request) {
        OrderModel order = new OrderModel();
        
        if (request.getDeliverymanId()!=null)
        {
            DeliverymanModel d = deliverymanRepository.findById(request.getDeliverymanId()).orElseThrow(()-> new NotFoundException("No existe el repartidor con id:"+ request.getDeliverymanId()));
            order.setDerliverymanId(d);
        }    
        CustomerModel c = customerRepository.findById(request.getCustomerId()).orElseThrow(()-> new NotFoundException("No existe el cliente con id:"+ request.getCustomerId()));               
        
        
        order.setCustomerId(c);
        try {          
            order.setStatus(OrderStatusModel.valueOf(request.getStatus()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para status" + ": " + request.getStatus());
        }
        
        order.setOrderDate(request.getOrderDate());
        order.setDeliveredDate(request.getDeliveredDate());

        try {          
            order.setPaymentType(PaymentModel.valueOf(request.getPayment()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Payment" + ": " + request.getPayment());
        }

        //calcular el total 
        order.setTotal(0);


        order = orderRepository.save(order); 

        return order;
    }

    @Transactional // Crear una transaccion
    public OrderModel actualizar(Integer id,OrderRequest request) {

       OrderModel order = orderRepository.findById(id).orElseThrow(()-> new NotFoundException("No existe la orden con id:"+ id));
        
    // OrderModel order = new OrderModel();
        
        if (request.getDeliverymanId()!=null)
        {
            DeliverymanModel d = deliverymanRepository.findById(request.getDeliverymanId()).orElseThrow(()-> new NotFoundException("No existe el repartidor con id:"+ request.getDeliverymanId()));
            order.setDerliverymanId(d);
        }    
        CustomerModel c = customerRepository.findById(request.getCustomerId()).orElseThrow(()-> new NotFoundException("No existe el cliente con id:"+ request.getCustomerId()));               
        
        
        order.setCustomerId(c);
        try {          
            order.setStatus(OrderStatusModel.valueOf(request.getStatus()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para status" + ": " + request.getStatus());
        }
        
        order.setOrderDate(request.getOrderDate());
        order.setDeliveredDate(request.getDeliveredDate());

        try {          
            order.setPaymentType(PaymentModel.valueOf(request.getPayment()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Payment" + ": " + request.getPayment());
        }

        //calcular el total 
        order.setTotal(0);


        order = orderRepository.save(order); 

        return order;
    }

    @Transactional(readOnly = true)
    public List<OrderModel> getOrders() {
        List<OrderModel> orders = new LinkedList<>();
        orderRepository.findAll().iterator().forEachRemaining(orders::add);
        return orders;
    }

    @Transactional(readOnly = true)
    public OrderModel getById(Integer id) {

        Optional<OrderModel> opt = orderRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NotFoundException();
    }

    @Transactional()
    public void delete(Integer id) {
        try {
            orderRepository.deleteById(id);                        
        } catch (Exception e) {}
        
    }

 
}