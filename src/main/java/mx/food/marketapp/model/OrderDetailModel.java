package mx.food.marketapp.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "order_detail")
public class OrderDetailModel {

    @EmbeddedId
    @JsonBackReference
    private OrderDetailKeyModel id; 

    @ManyToOne(optional=false,cascade = CascadeType.REMOVE)
    @JoinColumn(name="order_id", insertable=false, updatable=false)    
    @JsonBackReference
    OrderModel order; 

    @ManyToOne(optional=false)
    @JoinColumn(name="product_id",  insertable=false, updatable=false)
    ProductModel product;
 
    @Column(name = "amount")
    private int amount;

    @Column(name = "subtotal")
    private double subtotal;

    @Column(name = "finished")
    private boolean finished;

    @ManyToOne
    @JoinColumn(name="commerce_id")
    private CommerceModel commerce;

    public CommerceModel getCommerce() {
        return commerce;
    }


    public void setCommerce(CommerceModel commerceId) {
        this.commerce = commerceId;
    }


    public OrderDetailModel() {
    }
    

    public OrderDetailKeyModel getId() {
        return id;
    }

    public void setId(OrderDetailKeyModel id) {
        this.id = id;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
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
