package com.api.renascer.application.web.authentication;

import com.api.renascer.domain.dto.ApiResponse;
import com.api.renascer.domain.record.SignInRequest;
import com.api.renascer.domain.record.SignUpRequest;
import com.api.renascer.domain.record.TokenLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApi {

    @PostMapping(value = "/signin")
    ResponseEntity<ApiResponse<TokenLogin>> signIn(@RequestBody @Validated SignInRequest request);

    @PostMapping(value = "/signup")
    ResponseEntity<ApiResponse<String>> signUp(@RequestBody SignUpRequest request);

    @PostMapping("/check-email/{email}")
    ResponseEntity checkEmail(@PathVariable String email);

    @PostMapping("/recover-password/{email}")
    ResponseEntity recoverPassword(@PathVariable String email);
}
