package com.claimsmanagement.claimsmanagement.core.claim.web;

import com.claimsmanagement.claimsmanagement.core.claim.Claim;
import com.claimsmanagement.claimsmanagement.core.claim.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/claim")
public class ClaimController {
    @Autowired
    private ClaimService claimService;
    @RequestMapping("/{id}")
    public ClaimView getClaimById(@PathVariable("id") Long id){
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
