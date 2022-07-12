package com.claimsmanagement.claimsmanagement.core.claim.web;

import com.claimsmanagement.claimsmanagement.core.claim.Claim;
import com.claimsmanagement.claimsmanagement.core.claim.ClaimService;
import com.claimsmanagement.claimsmanagement.error.UnauthorizedException;
import com.claimsmanagement.claimsmanagement.util.MessageUtil;
import com.claimsmanagement.claimsmanagement.util.TokenValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/claim")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClaimController {
    private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);
    @Autowired
    private ClaimService claimService;
    @Autowired
    private TokenValidationUtil tokenValidationUtil;
    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping("/{id}")
    public ClaimView getClaimById(@RequestHeader(name = "Authorization") String tokenDup,
                                  @PathVariable("id") Long id) {
        if(tokenValidationUtil.getTokenStatus(tokenDup).getToken()){
            logger.info(String.format("Getting claim by id=%d",id));
            return claimService.getClaimById(id);
        }
        else {
            logger.info("Failed authentication");
            throw new UnauthorizedException("unauthorized");
        }
//        return claimService.getClaimById(id);
    }

    @GetMapping
    public Page<ClaimView> getAllClaim(@PageableDefault(sort = "claimId",direction = Sort.Direction.DESC)
                                           Pageable pageable,
                                       @RequestHeader(name = "Authorization") String tokenDup) {
        if (tokenValidationUtil.getTokenStatus(tokenDup).getToken()) {
            logger.info("Getting all claims");
            return claimService.getAllClaims(pageable);
        } else {
            logger.info("Failed authentication");
            throw new UnauthorizedException(messageUtil.getMessage("unauthorized"));
        }
//        return claimService.getAllClaims(pageable);
    }

    @GetMapping("/user/{userId}/view")
    public List<ClaimView> getAllClaimByMember(@RequestHeader(name = "Authorization") String tokenDup,
                                               @PathVariable("userId")Long userId){

        if(tokenValidationUtil.getTokenStatus(tokenDup).getToken()){
            logger.info(String.format("Getting claims for user %d",userId));
            return claimService.getAllClaimsByMember(userId);
        }
        else{
            logger.info("Failed authentication");
            throw new UnauthorizedException(messageUtil.getMessage("unauthorized"));
        }
//        return claimService.getAllClaimsByMember(userId);
    }
    @GetMapping("/pending")
    public List<ClaimView> getAllPendingClaims(/*@RequestHeader(name = "Authorization") String tokenDup*/){
        return claimService.getAllPendingClaims();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimView create(@RequestBody @Valid ClaimRequest claimBaseRequest,
                            @RequestHeader(name = "Authorization") String tokenDup) {
        if(tokenValidationUtil.getTokenStatus(tokenDup).getToken()){
            logger.info(String.format("Adding claim for user %d",claimBaseRequest.getMemberId()));
            return claimService.create(claimBaseRequest);
        }
        else{
            logger.info("Failed authentication");
            throw new UnauthorizedException(messageUtil.getMessage("unauthorized"));
        }
//        return claimService.create(claimBaseRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClaim(@PathVariable("id") Long id) {
        claimService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ClaimView updateClaim(@PathVariable("id") Long id
            , @RequestBody @Valid ClaimRequest claimBaseRequest,
                                 @RequestHeader(name="Authorization")String tokenDup) {
        if(tokenValidationUtil.getTokenStatus(tokenDup).getToken()){
            Claim claim = claimService.findClaimOrThrow(id);
            return claimService.update(claim, claimBaseRequest);
        }
        else {
            throw new UnauthorizedException(messageUtil.getMessage("unauthorized"));
        }
//        Claim claim = claimService.findClaimOrThrow(id);
//        return claimService.update(claim, claimBaseRequest);
    }
}
