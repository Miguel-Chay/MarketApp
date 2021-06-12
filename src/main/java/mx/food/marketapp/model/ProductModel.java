// package mx.food.marketapp.model;


// import javax.persistence.*;

// @Entity
// @Table(name = "product")
// public class ProductModel {

//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Integer id;


//   @OneToMany
//   @JoinColumn(name = "commerce_id")
//   private CommerceModel commerce;
  

//   @ManyToMany
//   @Column(name = "name")
//   private String name;

//   @Column(name = "description")
//   private String description;

//   @Column(name = "price")
//   private double price;

//   @Column(name = "stock")
//   private int stock;

//   public Integer getId() {
//     return id;
//   }

//   public void setId(Integer id) {
//     this.id = id;
//   }

//   public CommerceModel getCommerce() {
//     return commerce;
//   }

//   public void setCommerce(CommerceModel commerce) {
//     this.commerce = commerce;
//   }

//   public String getName() {
//     return name;
//   }

//   public void setName(String name) {
//     this.name = name;
//   }

//   public String getDescription() {
//     return description;
//   }

//   public void setDescription(String description) {
//     this.description = description;
//   }

//   public double getPrice() {
//     return price;
//   }

//   public void setPrice(double price) {
//     this.price = price;
//   }

//   public int getStock() {
//     return stock;
//   }

//   public void setStock(int stock) {
//     this.stock = stock;
//   }

  
   
// }
