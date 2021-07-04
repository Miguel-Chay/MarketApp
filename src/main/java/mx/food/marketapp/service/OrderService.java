package mx.food.marketapp.service;

import java.util.List;
import java.util.Optional;
// import java.sql.Date;
import java.util.Date;

// import com.mysql.cj.xdevapi.DatabaseObject.DbObjectStatus;

import java.util.LinkedList;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.food.marketapp.exception.*;
import mx.food.marketapp.model.OrderModel;
import mx.food.marketapp.model.OrderStatusModel;
import mx.food.marketapp.model.PaymentModel;
import mx.food.marketapp.model.UserModel;
import mx.food.marketapp.model.CustomerModel;
import mx.food.marketapp.model.DeliverymanModel;
import mx.food.marketapp.model.OrderDetailModel;
import mx.food.marketapp.model.request.OrderRequest;

import mx.food.marketapp.repository.OrderRepository;
import mx.food.marketapp.repository.ProductRepository;
import mx.food.marketapp.repository.UserRepository;
import mx.food.marketapp.repository.CustomerRepository;
import mx.food.marketapp.repository.DeliverymanRepository;
import mx.food.marketapp.repository.OrderDetailRepository;
import mx.food.marketapp.config.EmailSender;

@Service
public class OrderService {
    Double total = 0.0;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DeliverymanRepository deliverymanRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailSender emailSender;
    private String ProductosTotales = "";

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
            order.setStatus(OrderStatusModel.valueOf("ESPERA"));
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

        //Primero se crea la orden con valor cero por defecto 
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
        // order.setTotal(0);
        order.setTotal(this.calculateTotal(order.getId()));


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
    
    @Transactional()
    public Double calculateTotal(Integer id){
        this.getById(id);
        List<OrderDetailModel> oD = new LinkedList<>();
        oD = orderDetailRepository.findByOrderId( id);
        oD.stream().forEach((p)-> {
            System.out.println(p.getProduct().getName()+" : "+p.getProduct().getPrice()+" * "+p.getAmount()+" = "+p.getSubtotal());
            total+=p.getSubtotal();
        });
        return total;
    }

    @Transactional()
    public OrderModel actualizarTotal(Integer id){
        OrderModel orderModel = this.getById(id);
        orderModel.setTotal(this.calculateTotal(orderModel.getId()));
        
        return orderRepository.save(orderModel);
    }


    @Transactional()
    public List<OrderDetailModel> getProductsByOrder(Integer id){
        this.getById(id);
        List<OrderDetailModel> oD = new LinkedList<>();
        oD = orderDetailRepository.findByOrderId( id);
        // oD.stream().forEach((p)-> {});
        return oD;
    }


    @Transactional
    public OrderModel submit(Integer id){
        OrderModel orderModel = this.getById(id);
        this.actualizarTotal(id); //actualiza el total de price
        if(orderModel.getStatus()!=OrderStatusModel.valueOf("ESPERA"))
            throw new BadRequestException("Esta orden no puede ser comprada");
        List<OrderDetailModel> oD = new LinkedList<>();
        oD = orderDetailRepository.findByOrderId(id);
        ProductosTotales = "";
        oD.stream().forEach((p)-> {

            int stock=p.getProduct().getStock()-p.getAmount();
  
            if (stock<0)
                throw new BadRequestException("No hay cantidad disponible para cubrir la demanda de: "+p.getProduct().getName());
            p.getProduct().setStock(stock);
            productRepository.save(p.getProduct());
            if (p.isFinished())
                throw new BadRequestException("El producto "+p.getProduct().getName()+", que se encuentra en su carrito ya ha sido comprado");
            p.setFinished(true);
            orderDetailRepository.save(p);
            ProductosTotales +="\n" + p;

        });

        Date now =new Date();
        orderModel.setOrderDate(now);
 
        try {          
            orderModel.setStatus(OrderStatusModel.valueOf("COMPRADO"));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Hubo un error al realizar la compra");
        }
        //pal cliente
        UserModel user = userRepository.findById(orderModel.getCustomerId().getUser_id()).orElseThrow(()-> new NotFoundException("No existe el usuario con id:"+ orderModel.getCustomerId().getUser_id()));
        emailSender.enviarCorreo("Cliente "+user.getUsername()+ "su compra ha sido realizada.", user.getEmail(), "Compra realizada");
        //pal vendedor
        System.out.println("NOMBRE DEL VENDEDOR" + " tiene un nuevo pedido asignado por el cliente " 
        + user.getUsername() + ", y compró: \n\n" + ProductosTotales + ". \n\n Fue asignado al repartidor: " + orderModel.getCustomerId().getFirstname()
        + orderModel.getCustomerId().getLastname());
        //emailSender.enviarCorreo("Vendedor, " + "NOMBRE DEL VENDEDOR" + " tiene un nuevo pedido asignado por el cliente " 
        //+ user.getUsername() + ", y compró: \n\n" + ProductosTotales + ". \n\n Fue asignado al repartidor: " + orderModel.getCustomerId().getFirstname()
        //+ orderModel.getCustomerId().getLastname()
        //, "CORREO DEL VENDEDOR", "Pedido asignado" );
        return orderRepository.save(orderModel);
    }


    public OrderModel cancel(Integer id){
        OrderModel orderModel = this.getById(id);

        if(orderModel.getStatus()!=OrderStatusModel.valueOf("COMPRADO"))
            throw new BadRequestException("Esta orden no puede ser cancelada");


        List<OrderDetailModel> oD = new LinkedList<>();
        oD = orderDetailRepository.findByOrderId(id);
            
        oD.stream().forEach((p)-> {
    
            int stock=p.getProduct().getStock()+p.getAmount(); //devuelve los productos (productos en stock + productos comprados)
    
            p.getProduct().setStock(stock);
            productRepository.save(p.getProduct());

            p.setFinished(false);
            orderDetailRepository.save(p);
    
        });
    
        orderModel.setStatus(OrderStatusModel.valueOf("ESPERA"));//su carrito vuelve a estado de espera, para seguir comprando
        orderModel.setOrderDate(null);
        orderModel.setDeliveredDate(null);
        orderModel.setTotal(0);
        System.out.println(orderModel);
        System.out.println(orderModel.getDeliveredDate());
        orderRepository.save(orderModel);
        // ==================================================
        //                     CORREO
        //  (la compra ha sido cancelada, los productos han sido regresados al stock de los vendedores)
        // UserModel user = userRepository.findById(orderModel.getCustomerId().getUser_id()).orElseThrow(()-> new NotFoundException("No existe el usuario con id:"+ orderModel.getCustomerId().getUser_id()));
        // user.getEmail();
        // ==================================================
        UserModel user = userRepository.findById(orderModel.getCustomerId().getUser_id()).orElseThrow(()-> new NotFoundException("No existe el usuario con id:"+ orderModel.getCustomerId().getUser_id()));
        emailSender.enviarCorreo("Cliente " +user.getUsername()+ "su pedido ha sido cancelado, una disculpa.", user.getEmail(), "Pedido cancelado");
   

        return orderModel;
    }





}