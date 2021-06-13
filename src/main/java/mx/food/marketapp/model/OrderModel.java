package mx.food.marketapp.model;

import javax.persistence.*;
@Entity
@Table(name = "order")
public class OrderModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "delivery_man_id")
  private DeliverymanModel derliverymanId;

  @OneToOne
  @JoinColumn(name = "customer_id")
  private  CustomerModel customerId;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private OrderStatusModel status;

//   @OneToMany
  @Column(name = "payment_type")
  @Enumerated(EnumType.STRING)
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

public DeliverymanModel getDerliverymanId() {
    return derliverymanId;
}

public void setDerliverymanId(DeliverymanModel derliverymanId) {
    this.derliverymanId = derliverymanId;
}

public CustomerModel getCustomerId() {
    return customerId;
}

public void setCustomerId(CustomerModel customerId) {
    this.customerId = customerId;
}

public OrderStatusModel getStatus() {
    return status;
}

public void setStatus(OrderStatusModel status) {
    this.status = status;
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
