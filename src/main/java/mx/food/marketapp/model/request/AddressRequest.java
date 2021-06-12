package mx.food.marketapp.model.request;
import javax.validation.constraints.NotNull;

public class AddressRequest {

    @NotNull
    private String street;
    @NotNull
    private Integer customer_id;
    @NotNull
    private String crossing;
    @NotNull
    private String susburb;
    @NotNull
    private String city;
    
    public AddressRequest(){

    }
    
    public void setCustomerId(Integer customer_id) {
        this.customer_id = customer_id;
    }
 
    public Integer getCustomerId() {
        return customer_id;
    }

    public void setStreet(String street) {
        this.street = street;
    }
 
    public String getStreet() {
        return street;
    }
    public void setCrossing(String crossing) {
        this.crossing = crossing;
    }
 
    public String getCrossing() {
        return crossing;
    }
    public void setSuburb(String susburb) {
        this.susburb = susburb;
    }
 
    public String getSuburb() {
        return susburb;
    }

    public void setCity(String city) {
        this.city = city;
    }
 
    public String getCity() {
        return city;
    }






}
