package mx.food.marketapp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//import javax.transaction.Transactional;
import java.util.LinkedList;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.food.marketapp.exception.NotFoundException;
import mx.food.marketapp.model.UserModel;
import mx.food.marketapp.model.request.UserRequest;

import mx.food.marketapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private AlumnoRepository alumnoRepository;
// PUT, GET, GET by ID, DELETE para entidad User

    @Transactional // Crear una transaccion
    public UserModel actualizar(Integer id,UserRequest request) {

        UserModel user = userRepository.findById(id).orElseThrow(()-> new NotFoundException());

        user.setId(id);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        // String token = UUID.randomUUID().toString();
        // user.setToken(token);
        
        user = userRepository.save(user); 
        return user;
        
    }

    @Transactional(readOnly = true)
    public List<UserModel> getUsers() {
        List<UserModel> users = new LinkedList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @Transactional(readOnly = true)
    public UserModel getById(Integer id) {

        Optional<UserModel> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NotFoundException();
    }
    @Transactional()
    public void delete(Integer id) {
        try {
            userRepository.deleteById(id);                        
        } catch (Exception e) {}
        
    }

 
}