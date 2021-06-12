package mx.food.marketapp.model;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetailModel {
  @Id
  @Column(name = "order_id")
  private Integer orderId;
  
  @OneToMany
  @PrimaryKeyJoinColumn(name="order_id", referencedColumnName="order_id")
  private OrderModel order;

  
  @OneToMany
  @JoinColumn(name = "commerce_product_id")
  private  CommerceProductModel commerceProduct;

  @Column(name = "amount")
  private int amount;

  @Column(name = "subtotal")
  private double subtotal;

  @Column(name = "finished")
  private boolean finished;

  public OrderModel getOrder() {
    return order;
  }

  public void setOrder(OrderModel order) {
    this.order = order;
  }

  public CommerceProductModel getCommerceProduct() {
    return commerceProduct;
  }

  public void setCommerceProduct(CommerceProductModel commerceProduct) {
    this.commerceProduct = commerceProduct;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(double subtotal) {
    this.subtotal = subtotal;
  }

  public boolean isFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }

  
}
