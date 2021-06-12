package mx.food.marketapp.model.request;
// import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;
// import mx.food.marketapp.model.VehicleModel;
// import javax.validation.constraints.Pattern;
public class DeliverymanRequest {
 
//DATOS DE SALESMAN
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String cellphone;
    @NotNull
    private String sex;
    @NotNull
    private String city;
    @NotNull
    private String address;
    @NotNull
    private String vehicle;

    public DeliverymanRequest() {

    }
 

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
 
    public String getFirstname() {
        return firstname;
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
    public void setSex(String sex) {
        this.sex = sex;
    }
 
    public String getSex() {
        return sex;
    }
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getCity() {
        return city;
    }
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getAddress() {
        return address;
    }
    
    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }


}