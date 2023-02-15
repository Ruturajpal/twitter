package com.jwt.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.token.helper.JwtUtil;
import com.jwt.token.model.JwtRequest;
import com.jwt.token.model.JwtResponse;
import com.jwt.token.services.CustomeUserDetailServices;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomeUserDetailServices customeUserDetailServices;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@RequestMapping(value ="/token" ,method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest);
		
		
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					(jwtRequest.getUsername(),jwtRequest.getPassword()));
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("bad credentials");
		}
		
		UserDetails userDetails=this.customeUserDetailServices.loadUserByUsername(jwtRequest.getUsername());
		
		String token=this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT ,"+token);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
