package mx.food.marketapp.service;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.food.marketapp.model.UserModel;
import mx.food.marketapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import mx.food.marketapp.config.JwtTokenUtil;

@Service
public class JwtUserService implements UserDetailsService{
	
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

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

	public String authToken(String username)  {
		UserModel user = userRepository.findByUsername(username);

		if (user!=null) {

			UserDetails userDetails = new User(user.getUsername(), user.getPassword(),	new ArrayList<>());
			String token = jwtTokenUtil.generateToken(userDetails);

			// user = userRepository.save(user); 
			
			return token;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}


	

}


 
