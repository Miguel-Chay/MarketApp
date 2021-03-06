package mx.food.marketapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class UserModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "username")
    private String username;
    
    @Column
    @JsonIgnore
    private String password;
    
    @Column(name = "email")
    private String email;

    @Column(name = "type")
    private String type;

    
 
    public UserModel() {
    }

    public UserModel(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username= username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getPassword() {
        return password;
    }

    public void setType(String type) {
        this.type = type;
    }
 
    public String getType() {
        return type;
    }



    public UserModel id(Integer id) {
        this.id = id;
        return this;
    }

    public UserModel user(String username) {
        this.username = username;
        return this;
    }

    
}