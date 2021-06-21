package mx.food.marketapp.model;
import java.io.Serializable;
import javax.persistence.*;


@Embeddable
public class OrderDetailKeyModel  implements Serializable{


  // @Column(name="order_id", insertable=false, updatable=false)
  // private Integer orderId; 

  // @Column(name="product_id",insertable=false, updatable=false)
  // private Integer productId;
  @ManyToOne(optional=false)
  @JoinColumn(name="order_id", insertable=false, updatable=false)
  
  OrderModel orderId; 

  @ManyToOne(optional=false)
  @JoinColumn(name="product_id",  insertable=false, updatable=false)
  ProductModel productId;

  public OrderModel getOrderId() {
    return orderId;
  }

  public void setOrderId(OrderModel orderId) {
    this.orderId = orderId;
  }

  public ProductModel getProductId() {
    return productId;
  }

  public void setProductId(ProductModel productId) {
    this.productId = productId;
  }


  
  // public OrderModel getOrder() {
  //   return order;
  // }

  // public void setOrder(OrderModel order) {
  //   this.order = order;
  // }

  // public ProductModel getProduct() {
  //   return product;
  // }

  // public void setProduct(ProductModel product) {
  //   this.product = product;
  // }
  
  
}
