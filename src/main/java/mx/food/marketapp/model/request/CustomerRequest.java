package mx.food.marketapp.model.request;
// import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

// import mx.food.marketapp.model.PaymentModel;

// import mx.food.marketapp.model.VehicleModel;

// import javax.validation.constraints.Pattern;
public class CustomerRequest {
  
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
    private String address_id;
    @NotNull
    private String paymentType;
 

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

    public String getPayment() {
        return paymentType;
    }
    public void setPayment(String payment) {
        this.paymentType = payment;
    }


}