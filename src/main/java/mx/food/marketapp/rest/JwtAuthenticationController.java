package mx.food.marketapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URISyntaxException;
import javax.validation.Valid;
import mx.food.marketapp.service.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import mx.food.marketapp.exception.*;

import mx.food.marketapp.model.UserModel;
import mx.food.marketapp.model.request.JwtRequest;
import mx.food.marketapp.model.request.JwtResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;


	@Autowired
	private JwtUserService userDetailsService;
	


    @PostMapping("/register")
    public ResponseEntity<UserModel> postRegister(@RequestBody @Valid JwtRequest request) throws Exception {
        UserModel user = userDetailsService.register(request);
        return ResponseEntity.created(new URI("/users/" + user.getId())).body(user);
    }


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
 
		final String token = userDetailsService.authToken(authenticationRequest.getUsername());
 
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			// System.out.println("termina authenticate");
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}