package mx.food.marketapp.service;

import java.util.List;
import java.util.Optional;

import com.rabbitmq.client.AMQP.Basic.Deliver;

import java.util.Date;
import java.util.LinkedList;

import org.springframework.transaction.annotation.Transactional;
// import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.food.marketapp.exception.BadRequestException;
import mx.food.marketapp.exception.NotFoundException;
import mx.food.marketapp.model.CityModel;
import mx.food.marketapp.model.DeliverymanModel;
import mx.food.marketapp.model.OrderModel;
import mx.food.marketapp.model.OrderStatusModel;
import mx.food.marketapp.model.SexModel;
import mx.food.marketapp.model.UserModel;
import mx.food.marketapp.model.VehicleModel;
import mx.food.marketapp.model.request.RegisterDeliverymanRequest;
import mx.food.marketapp.model.request.DeliverymanRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import mx.food.marketapp.repository.DeliverymanRepository;
import mx.food.marketapp.repository.OrderRepository;
import mx.food.marketapp.repository.UserRepository;
import mx.food.marketapp.config.EmailSender;

@Service
public class DeliverymanService {

    @Autowired
    private DeliverymanRepository deliverymanRepository;
    @Autowired
    private UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EmailSender emailSender;
    

    @Transactional // Crear una transaccion
    public DeliverymanModel registrar(RegisterDeliverymanRequest request) {

        UserModel u = userRepository.findByUsername(request.getUsername());
        if (u!=null){
            throw new BadRequestException("El valor del campo username no esta disponible");
        }

        UserModel newuser = new UserModel();

        newuser.setUsername(request.getUsername());
        newuser.setPassword(passwordEncoder.encode(request.getPassword()));
        newuser.setEmail(request.getEmail());
        newuser.setType("deliveryman");
        newuser = userRepository.save(newuser); 
              
        DeliverymanModel newDeliveryman = new DeliverymanModel();
        newDeliveryman.setUser_id(newuser.getId());
        newDeliveryman.setFirstname(request.getFirstname());
        newDeliveryman.setLastname(request.getLastname());
        newDeliveryman.setCellphone(request.getCellphone());
        newDeliveryman.setAddress(request.getAddress());
        try {          
            newDeliveryman.setCity(CityModel.valueOf(request.getCity()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para City" + ": " + request.getCity());
        }
        try {          
            newDeliveryman.setSex(SexModel.valueOf(request.getSex()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Sex" + ": " + request.getSex());
        }
        try {          
            newDeliveryman.setVehicle(VehicleModel.valueOf(request.getVehicle()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Vehicle" + ": " + request.getVehicle());
        }
        // newDeliveryman.setUser(newuser); // Relacionar 2 entidades
        newDeliveryman = deliverymanRepository.save(newDeliveryman); 



        // ==================================================
        //                     CORREO
        // ==================================================
        //DeliverymanModel name= new DeliverymanModel();
        //name = userRepository.findById();
        //preguntar como obtener el nombre
        //UserModel user = userRepository.findById(order.getCustomerId().getUser_id()).orElseThrow(()-> new NotFoundException("No existe el usuario con id:"+ order.getCustomerId().getUser_id()));
        
        emailSender.enviarCorreo("Bienvenid@ a MarketApp \n"+newuser.getUsername(), newuser.getEmail(), "Bienvenido repartidor");
        
        System.out.println("COREOOO.... \n"+"Bienvenid@ a MarketApp"+newuser.getUsername() + " " +newuser.getEmail()+ "Bienvenido repartidor");
        return newDeliveryman;
        
    } 


    @Transactional // Crear una transaccion
    public DeliverymanModel actualizar(Integer id,DeliverymanRequest request) {

        DeliverymanModel deliveryman = deliverymanRepository.findById(id).orElseThrow(()-> new NotFoundException());
         
    
        deliveryman.setFirstname(request.getFirstname());
        deliveryman.setLastname(request.getLastname());
        deliveryman.setCellphone(request.getCellphone());
        deliveryman.setAddress(request.getAddress());
        try {          
            deliveryman.setCity(CityModel.valueOf(request.getCity()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para City" + ": " + request.getCity());
        }
        try {          
            deliveryman.setSex(SexModel.valueOf(request.getSex()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Sex" + ": " + request.getSex());
        }
        try {          
            deliveryman.setVehicle(VehicleModel.valueOf(request.getVehicle()));
        } catch (IllegalArgumentException e) {                   
            throw new BadRequestException("Valor invalido para Vehicle" + ": " + request.getVehicle());
        }
        // newDeliveryman.setUser(newuser); // Relacionar 2 entidades
        deliveryman = deliverymanRepository.save(deliveryman); 
        
        return deliveryman;
    }

    @Transactional(readOnly = true)
    public List<DeliverymanModel> getDeliverymans() {
        List<DeliverymanModel> deliverymans = new LinkedList<>();
        deliverymanRepository.findAll().iterator().forEachRemaining(deliverymans::add);
        return deliverymans;
    }

    @Transactional(readOnly = true)
    public DeliverymanModel getById(Integer id) {

        Optional<DeliverymanModel> opt = deliverymanRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NotFoundException();
    }
    
    @Transactional()
    public void delete(Integer id) {
        try {
            deliverymanRepository.deleteById(id);                        
        } catch (Exception e) {}
        
    }


    public List<OrderModel> getOrdersavailable(){
        List<OrderModel> orders = new LinkedList<>();
        orders = orderRepository.findByDeliverymanAndStatus(null,OrderStatusModel.valueOf("COMPRADO"));
        return orders;
    }

    public OrderModel submit(Integer deliverymanId, Integer orderId){
        
        OrderModel order = orderRepository.findById(orderId).orElseThrow(()->new BadRequestException("No existe la orden con id:"+orderId));
        DeliverymanModel deliveryman = deliverymanRepository.findById(deliverymanId).orElseThrow(()->new BadRequestException("No existe el repartidor con id:"+deliverymanId));

        if (order.getStatus()!=OrderStatusModel.valueOf("COMPRADO")){
            throw new BadRequestException("La compra no se puede entregar");
        }
        order.setStatus(OrderStatusModel.valueOf("ENVIANDO"));
        order.setDerliverymanId(deliveryman);

        orderRepository.save(order);
        
        //correo pal usuario
        UserModel user = userRepository.findById(order.getCustomerId().getUser_id()).orElseThrow(()-> new NotFoundException("No existe el usuario con id:"+ order.getCustomerId().getUser_id()));
        emailSender.enviarCorreo("Cliente "+user.getUsername()+ "su pedido está en camino", user.getEmail(), "Pedido en camino");
        //correo pal repartidor 
        emailSender.enviarCorreo("Repartidor, "+ deliveryman.getUser().getUsername()+" tiene un nuevo pedido asignado del comercio \n\n"
        ,deliveryman.getUser().getEmail(), "Nuevo pedido asignado");
        
        return order;
    }

    public OrderModel delivered(Integer deliverymanId, Integer orderId){
        OrderModel order = orderRepository.findById(orderId).orElseThrow(()->new BadRequestException("No existe la orden con id:"+orderId));
        deliverymanRepository.findById(deliverymanId).orElseThrow(()->new BadRequestException("No existe el repartidor con id:"+deliverymanId));
        if (order.getStatus()!=OrderStatusModel.valueOf("ENVIANDO")){
            throw new BadRequestException("La compra no se puede entregar");
        }
        order.setStatus(OrderStatusModel.valueOf("ENTREGADO"));
        Date now =new Date();
        order.setDeliveredDate(now);
        orderRepository.save(order);

        // ==================================================
        //                     CORREO (se ha realizado la entrega de su pedido)
        // UserModel user = userRepository.findById(order.getCustomerId().getUser_id()).orElseThrow(()-> new NotFoundException("No existe el usuario con id:"+ order.getCustomerId().getUser_id()));
        // user.getEmail();
        // ==================================================
        UserModel user = userRepository.findById(order.getCustomerId().getUser_id()).orElseThrow(()-> new NotFoundException("No existe el usuario con id:"+ order.getCustomerId().getUser_id()));
        emailSender.enviarCorreo("Cliente "+user.getUsername()+ "su pedido ha sido entregado.", user.getEmail(), "Pedido entregado");
       
        return order;
    }
 

}