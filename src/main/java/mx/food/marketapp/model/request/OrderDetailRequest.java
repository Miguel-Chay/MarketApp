package mx.food.marketapp.model.request;
import javax.validation.constraints.NotNull;

public class OrderDetailRequest {
    
    @NotNull
    private Integer orderId; 

    @NotNull
    private Integer productId;

    @NotNull
    private Integer amount;

    // @NotNull
    // private Double subtotal;

    @NotNull
    private boolean finished;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    // public Double getSubtotal() {
    //     return subtotal;
    // }

    // public void setSubtotal(Double subtotal) {
    //     this.subtotal = subtotal;
    // }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }


}
