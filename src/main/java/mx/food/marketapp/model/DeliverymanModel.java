package mx.food.marketapp.model;

// import java.beans.VetoableChangeListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToOne;
// import javax.persistence.JoinColumn;

@Entity
@Table(name = "deliveryman")
public class DeliverymanModel{
    
    @Id
    @Column(name = "user_id")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    

    // @OneToOne
    // @JoinColumn(name = "id") // alumno.id_user
    // private UserModel user;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "cellphone")
    private String cellphone;
    
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private SexModel sex;

    @Column
    @Enumerated(EnumType.STRING)
    private CityModel city;

    @Column
    @Enumerated(EnumType.STRING)
    private VehicleModel vehicle;
    
    @Column(name = "address")
    private String address;
    
    public DeliverymanModel() {
    }

    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id= user_id;
    }
 
    // public void setUser(UserModel user) {
    //     this.user = user;
    // }
    // public UserModel getUser() {
    //     return this.user ;
    // }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
 
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
 
    public String getLastname() {
        return lastname;
    }
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
 
    public String getCellphone() {
        return cellphone;
    }
    public void setSex(SexModel sex) {
        this.sex = sex;
    }
 
    public SexModel getSex() {
        return sex;
    }
    public void setCity(CityModel city) {
        this.city = city;
    }
 
    public CityModel getCity() {
        return city;
    }
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getAddress() {
        return address;
    }
    public void setVehicle(VehicleModel vehicle) {
        this.vehicle = vehicle;
    }
 
    public VehicleModel getVehicle() {
        return vehicle;
    }
    
}