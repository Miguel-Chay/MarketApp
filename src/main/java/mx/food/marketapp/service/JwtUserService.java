package mx.food.marketapp.service;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import mx.food.marketapp.exception.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mx.food.marketapp.model.UserModel;
import mx.food.marketapp.repository.UserRepository;
import mx.food.marketapp.config.JwtTokenUtil;
// import mx.food.marketapp.model.request.JwtRequest;
import mx.food.marketapp.model.request.UserRequest;

@Service
public class JwtUserService implements UserDetailsService{
	
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepository.findByUsername(username);
		if (user!=null) {
			return new User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	// @Transactional // Crear una transaccion
    public UserModel register(UserRequest request) {
        UserModel user = userRepository.findByUsername(request.getUsername());
        if (user!=null){
            throw new BadRequestException("El valor del campo username no esta disponible");
        }
        UserModel userCreate = new UserModel();
        userCreate.setUsername(request.getUsername());
        userCreate.setPassword(passwordEncoder.encode(request.getPassword()));
				userCreate.setEmail(request.getEmail());
        // UserDetails userDetails = new User(userCreate.getUsername(), userCreate.getPassword(),	new ArrayList<>());
        // String token = jwtTokenUtil.generateToken(userDetails);
        UserModel userSaved = userRepository.save(userCreate); 

        return userSaved;
    }
	
	@Transactional 
	public String authToken(String username)  {
		UserModel user = userRepository.findByUsername(username);
		if (user!=null) {
			UserDetails userDetails = new User(user.getUsername(), user.getPassword(),	new ArrayList<>());
			String token = jwtTokenUtil.generateToken(userDetails);
			
			return token;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	@Transactional 
    public UserModel self(String username) {
        return userRepository.findByUsername(username);
    }

	

}


 
