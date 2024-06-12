package com.employee.controller;

import com.employee.entity.JwtRequest;
import com.employee.entity.JwtResponse;
import com.employee.entity.RefreshToken;
import com.employee.entity.RefreshTokenRequest;
import com.employee.security.JwtHelper;
import com.employee.service.RefreshTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
//	@Qualifier("authenticationManagerBean")
	private AuthenticationManager manager;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

		this.doAuthenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = JwtHelper.generateToken(userDetails);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getUsername());
		JwtResponse response = new JwtResponse(token, refreshToken.getToken());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String username, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	@PostMapping("/refreshToken")
	public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequestDTO){
		return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
				.map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUserInfo)
				.map(userInfo -> {
					UserDetails userDetails = userDetailsService.loadUserByUsername(userInfo.getUsername());
					String token = JwtHelper.generateToken(userDetails);


					JwtResponse jwtResponse = new JwtResponse();
					jwtResponse.setToken(token);
					jwtResponse.setUsername(userDetails.getUsername());
					return jwtResponse;
				}).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
	}
}
