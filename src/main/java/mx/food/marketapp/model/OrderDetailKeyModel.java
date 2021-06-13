package mx.food.marketapp.model;
import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class OrderDetailKeyModel  implements Serializable{


  @Column(name="order_id", insertable=false, updatable=false)
  private Integer orderId; 

  @Column(name="product_id",insertable=false, updatable=false)
  private Integer productId;

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  
}
