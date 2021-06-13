package mx.food.marketapp.model.request;
import javax.validation.constraints.NotNull;

public class ProductRequest {
     
    @NotNull
    private Integer commerceId;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;    

    public Integer getCommerceId() {
        return commerceId;
    }
    public void setCommerceId(Integer commerceId) {
        this.commerceId = commerceId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
