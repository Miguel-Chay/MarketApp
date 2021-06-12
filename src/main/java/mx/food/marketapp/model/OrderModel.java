package mx.food.marketapp.model;

import javax.persistence.*;
@Entity
@Table(name = "order")
public class OrderModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToMany
  @JoinColumn(name = "delivery_man_id")
  private DeliverymanModel derliveryman;

  @OneToMany
  @JoinColumn(name = "customer_id")
  private  CustomerModel customer;

  @OneToOne
  @JoinColumn(name = "order_satatus_id")
  private  OrderStatusModel orderStatus;

  @OneToMany
  @JoinColumn(name = "payment_type_id")
  private  PaymentModel paymentType;

  @Column(name = "order_date")
  private String orderDate;

  @Column(name = "delivered_date")
  private String deliveredDate;

  @Column(name = "total")
  private double total;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public DeliverymanModel getDerliveryman() {
    return derliveryman;
  }

  public void setDerliveryman(DeliverymanModel derliveryman) {
    this.derliveryman = derliveryman;
  }

  public CustomerModel getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerModel customer) {
    this.customer = customer;
  }

  public OrderStatusModel getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatusModel orderStatus) {
    this.orderStatus = orderStatus;
  }

  public PaymentModel getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentModel paymentType) {
    this.paymentType = paymentType;
  }

  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }

  public String getDeliveredDate() {
    return deliveredDate;
  }

  public void setDeliveredDate(String deliveredDate) {
    this.deliveredDate = deliveredDate;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  


}
