package com.claimsmanagement.claimsmanagement.core.claim.web;

import com.claimsmanagement.claimsmanagement.core.claim.Claim;
import com.claimsmanagement.claimsmanagement.core.claim.ClaimService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import javax.validation.Valid;

@RestController
@RequestMapping("/claim")
public class ClaimController {
	private static final Logger logger=LoggerFactory.getLogger(ClaimController.class);
    @Autowired
    private ClaimService claimService;
    @RequestMapping("/{id}")
    public ClaimView getClaimById(@RequestHeader(name = "Authorization") String tokenDup,@PathVariable("id") Long id){
    	HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("tokenDup",tokenDup);
    	ResponseEntity<TokenValidation> status= new RestTemplate().getForEntity
   			("http://localhost:8080/api/auth/validate/{tokenDup}", 
   					TokenValidation.class, uriVariables);
   	TokenValidation token=status.getBody();
    	
  	logger.info(token.toString());
        return claimService.getClaimById(id);
    }
    @GetMapping
    @ResponseBody
    public Page<ClaimView> getAllClaim(@PageableDefault(sort = "claimId",
            direction = Sort.Direction.DESC) Pageable pageable){
        return claimService.getAllClaims(pageable);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimView create(@RequestBody @Valid ClaimRequest claimBaseRequest){
        return claimService.create(claimBaseRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClaim(@PathVariable("id") Long id){
        claimService.delete(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ClaimView updateClaim(@PathVariable("id") Long id
            ,@RequestBody @Valid ClaimRequest claimBaseRequest){
        Claim claim=claimService.findClaimOrThrow(id);
        return claimService.update(claim,claimBaseRequest);
    }
}
