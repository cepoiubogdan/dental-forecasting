package com.example.demo.auth;


import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/v1/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) throws BadRequestException {
        AuthenticationResponse response = service.register(registerRequest);
        if (response == null ) {
          throw new BadRequestException("This username already exists");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationrRequest registerRequest){
        return ResponseEntity.ok(service.authenticate(registerRequest));
    }

}
