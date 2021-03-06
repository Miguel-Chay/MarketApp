package mx.food.marketapp.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 

// import mx.food.marketapp.model.VehicleModel;

import javax.validation.constraints.Pattern;
public class RegisterCustomerRequest {
//DATOS DEL USUARIO
    @NotNull
    @Size(min = 5, max = 45)
    @NotEmpty
    private String username;
    @NotNull
    @Size(min = 5, max = 255)
    @NotEmpty
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",message="La contraseña debe mayor de 8 caracteres y contener letras, numeros y al menos 1 caracter especial.")      
    private String password;
    @NotNull  //^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$
    @Pattern(regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
    private String email;
    // @NotNull
    // private String type;
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
    private String payment;

    public RegisterCustomerRequest() {

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email= email;
    }
 
    
    // public void setType(String type) {
    //     this.type = type;
    // }
 
    // public String getType() {
    //     return type;
    // }

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
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }


}