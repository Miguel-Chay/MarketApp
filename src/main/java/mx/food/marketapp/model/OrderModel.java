// package mx.food.marketapp.model;

// import javax.persistence.*;
// @Entity
// @Table(name = "order")
// public class OrderModel {

//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Integer id;

//   @OneToMany
//   @JoinColumn(name = "delivery_man_id")
//   private DeliverymanModel derliveryman;

//   @OneToMany
//   @JoinColumn(name = "customer_id")
//   private  CustomerModel customer;

//   @Column(name = "status")
//   @Enumerated(EnumType.STRING)
//   private OrderStatusModel status;

//   @OneToMany
//   @JoinColumn(name = "payment_type")
//   @Enumerated(EnumType.STRING)
//   private  PaymentModel paymentType;

//   @Column(name = "order_date")
//   private String orderDate;

//   @Column(name = "delivered_date")
//   private String deliveredDate;

//   @Column(name = "total")
//   private double total;
// }
