package mx.food.marketapp.model;

import java.sql.Date;

import javax.persistence.*;
@Entity
@Table(name = "car")
public class OrderModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "deliveryman_id")
  private DeliverymanModel deliveryman;

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
  private Date orderDate;

  @Column(name = "delivered_date")
  private Date deliveredDate;

  @Column(name = "total")
  private double total;

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public DeliverymanModel getDerliverymanId() {
    return deliveryman;
}

public void setDerliverymanId(DeliverymanModel derliverymanId) {
    this.deliveryman = derliverymanId;
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

public Date getOrderDate() {
    return orderDate;
}

public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
}

public Date getDeliveredDate() {
    return deliveredDate;
}

public void setDeliveredDate(Date deliveredDate) {
    this.deliveredDate = deliveredDate;
}

public double getTotal() {
    return total;
}

public void setTotal(double total) {
    this.total = total;
}



  
}
