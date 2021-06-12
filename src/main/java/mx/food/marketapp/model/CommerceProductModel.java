package mx.food.marketapp.model;


import javax.persistence.*;

@Entity
@Table(name = "commerce_product")
public class CommerceProductModel {
  @Id
  @Column(name = "commerce_id")
  private Integer commerceId;
  
  @OneToOne
  @PrimaryKeyJoinColumn(name="commerce_id", referencedColumnName="commerce_id")
  private CommerceModel commerce;

  @ManyToMany
  @JoinColumn(name = "product_id")
  private ProductModel product;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private double price;

  @Column(name = "stock")
  private int stock;

  public Integer getCommerceId() {
    return commerceId;
  }

  public void setCommerceId(Integer commerceId) {
    this.commerceId = commerceId;
  }

  public CommerceModel getCommerce() {
    return commerce;
  }

  public void setCommerce(CommerceModel commerce) {
    this.commerce = commerce;
  }

  public ProductModel getProduct() {
    return product;
  }

  public void setProduct(ProductModel product) {
    this.product = product;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  
  
}
