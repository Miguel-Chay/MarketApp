package mx.food.marketapp.rest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.food.marketapp.model.UserModel;
import mx.food.marketapp.model.request.UserRequest;
import mx.food.marketapp.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRest {
// PUT, GET, GET by ID, DELETE para entidad User
    @Autowired
    private UserService userService;


    @PutMapping("/users/{id}")
    public ResponseEntity<UserModel> actualizarUser(@PathVariable Integer id, @Valid @RequestBody UserRequest request) {
        UserModel u = userService.actualizar(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> obtenerUser() {
        List<UserModel> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Integer id) {
        UserModel u = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/quienSoy")
    // public ResponseEntity<User> getLoggedUser(){
    //     User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     return ResponseEntity.ok(user); 
    // }

    // @PostMapping("/register")
    // public ResponseEntity<User> registrarUser(@RequestBody UserRequest request) {
    //     User u = userService.crear(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(u);
    // }

    
    

}