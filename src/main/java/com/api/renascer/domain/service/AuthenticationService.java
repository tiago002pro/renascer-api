package com.api.renascer.domain.service;

import com.api.renascer.domain.record.SignInRequest;
import com.api.renascer.domain.record.SignUpRequest;
import com.api.renascer.domain.record.TokenLogin;

public interface AuthenticationService {

    TokenLogin signIn(SignInRequest request);

    String signUp(SignUpRequest request);

    String checkEmail(String email);

    String recoverPassword(String email);
}
