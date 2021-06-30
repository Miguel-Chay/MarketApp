package mx.food.marketapp.model.request;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class OrderRequest {
    // @NotNull
    private Integer deliverymanId;
    @NotNull
    private Integer customerId;
    // @NotNull
    private String status;
    @NotNull
    private Date orderDate;
    @NotNull
    private Date deliveredDate;    
    @NotNull
    private String payment;
    public Integer getDeliverymanId() {
        return deliverymanId;
    }
    public void setDeliverymanId(Integer deliverymanId) {
        this.deliverymanId = deliverymanId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }    



    
}
