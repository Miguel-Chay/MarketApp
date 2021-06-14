package mx.food.marketapp.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
public class UserRequest {

    @NotNull
    @Size(min = 5, max = 45)
    @NotEmpty
    private String username;

   
    @NotNull
    @Size(min = 5, max = 255)
    @NotEmpty
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",message="La contraseña debe mayor de 8 caracteres y contener letras, numeros y al menos 1 caracter especial.")      
    private String password;

    @NotNull
    @NotEmpty
    private String email;

    private String type;

    public UserRequest() {

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
 
    
    public void setType(String type) {
        this.type = type;
    }
 
    public String getType() {
        return type;
    }



}