package mx.food.marketapp.model.request;
import javax.validation.constraints.NotNull;

public class AddressRequest {

    @NotNull
    private String street;
    @NotNull
    private Integer customerId;
    @NotNull
    private String crossing;
    @NotNull
    private String suburb;
    @NotNull
    private String city;
    
    public AddressRequest(){

    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
 
    public Integer getCustomerId() {
        return customerId;
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
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
 
    public String getSuburb() {
        return suburb;
    }

    public void setCity(String city) {
        this.city = city;
    }
 
    public String getCity() {
        return city;
    }






}
