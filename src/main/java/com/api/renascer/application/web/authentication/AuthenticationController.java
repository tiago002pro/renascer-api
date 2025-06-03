package com.api.renascer.application.web.authentication;

import com.api.renascer.domain.dto.ApiResponse;
import com.api.renascer.domain.record.SignInRequest;
import com.api.renascer.domain.record.SignUpRequest;
import com.api.renascer.domain.record.TokenLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    @Override
    public ResponseEntity<ApiResponse<TokenLogin>> signIn(SignInRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<String>> signUp(SignUpRequest request) {
        return null;
    }

    @Override
    public ResponseEntity checkEmail(String email) {
        return null;
    }

    @Override
    public ResponseEntity recoverPassword(String email) {
        return null;
    }
}
