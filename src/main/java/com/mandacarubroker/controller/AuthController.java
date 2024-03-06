package com.mandacarubroker.controller;

import com.mandacarubroker.model.User;
import com.mandacarubroker.request.RequestLoginDTO;
import com.mandacarubroker.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  public AuthController(TokenService tokenService,AuthenticationManager authenticationManager){
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping()
  public String login(@RequestBody RequestLoginDTO login) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.username(), login.password());

    Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    var user = (User) authenticate.getPrincipal();

    return tokenService.generateToken(user);
  }
}
