// package mx.food.marketapp.model;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.EnumType;
// import javax.persistence.Enumerated;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToMany;
// import javax.persistence.OneToMany;
// import javax.persistence.OneToOne;
// import javax.persistence.PrimaryKeyJoinColumn;
// import javax.persistence.Table;

// @Entity
// @Table(name = "customer")
// public class CustomerModel {
  
//   @Id
//   @Column(name = "user_id")
//   private Integer user_id;
  
//   @OneToOne
//   @PrimaryKeyJoinColumn(name="user_id", referencedColumnName="user_id")
//   private UserModel user;

//   @ManyToMany
//   @JoinColumn(name = "payment_type")
//   private PaymentModel paymentType;

//   @Column(name = "firstname")
//   private String firstname;

//   @Column(name = "lastname")
//   private String lastname;

//   @Column(name = "cellphone")
//   private String cellphone;

//   @Column(name = "sex")
//   @Enumerated(EnumType.STRING)
//   private SexModel sex;

//   public Integer getUser_id() {
//     return user_id;
//   }

//   public void setUser_id(Integer user_id) {
//     this.user_id = user_id;
//   }

//   public UserModel getUser() {
//     return user;
//   }

//   public void setUser(UserModel user) {
//     this.user = user;
//   }

//   public PaymentModel getpayment() {
//     return paymentType;
//   }

//   public void setpayment(PaymentModel paymentType) {
//     this.paymentType = paymentType;
//   }

//   public String getFirstname() {
//     return firstname;
//   }

//   public void setFirstname(String firstname) {
//     this.firstname = firstname;
//   }

//   public String getLastname() {
//     return lastname;
//   }

//   public void setLastname(String lastname) {
//     this.lastname = lastname;
//   }

//   public String getCellphone() {
//     return cellphone;
//   }

//   public void setCellphone(String cellphone) {
//     this.cellphone = cellphone;
//   }

//   public SexModel getSex() {
//     return sex;
//   }

//   public void setSex(SexModel sex) {
//     this.sex = sex;
//   }

  
// }
