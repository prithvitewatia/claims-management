package com.claimsmanagement.claimsmanagement.util;

import com.claimsmanagement.claimsmanagement.core.claim.web.TokenValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
@Service
public class TokenValidationUtil {
    public TokenValidation getTokenStatus(String tokenDup){
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("tokenDup", tokenDup);
        ResponseEntity<TokenValidation> status = new RestTemplate().getForEntity
                ("http://localhost:8080/api/auth/validate/{tokenDup}",
                        TokenValidation.class, uriVariables);
        return status.getBody();
    }
}
