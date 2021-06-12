package mx.food.marketapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "address")
public class AddressModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column(name = "customer_id")
    // private Integer customerId;

    @Column(name = "street")
    private String street;

    @Column(name = "crossing")
    private String crossing;

    @Column(name = "suburb")
    private String suburb;
    
    @Column(name = "city")
    @Enumerated(EnumType.STRING)
    private CityModel city;
    
    @OneToOne()
    @JoinColumn(name = "customer_id" )
    private CustomerModel customer;

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public AddressModel() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id= id;
    }
    // public Integer getCustomerId() {
    //     return customerId;
    // }
    // public void setCustomerId(Integer customerId) {
    //     this.customerId= customerId;
    // }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
 
    public void setCrossing(String crossing) {
        this.crossing = crossing;
    }
 
    public String getCrossing() {
        return crossing;
    }
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
 
    public String getSuburb() {
        return suburb;
    } 
    public void setCity(CityModel city) {
        this.city = city;
    }
 
    public CityModel getCity() {
        return city;
    } 
    
}