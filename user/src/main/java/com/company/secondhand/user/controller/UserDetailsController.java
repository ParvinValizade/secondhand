package com.company.secondhand.user.controller;

import com.company.secondhand.user.dto.CreateUserDetailsRequest;
import com.company.secondhand.user.dto.UpdateUserDetailsRequest;
import com.company.secondhand.user.dto.UserDetailsDto;
import com.company.secondhand.user.service.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-details")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> createUserDetails(@RequestBody CreateUserDetailsRequest request){
        return ResponseEntity.ok(userDetailsService.createUserDetails(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable Long id,
                                                            @RequestBody UpdateUserDetailsRequest request){
    return ResponseEntity.ok(userDetailsService.updateUserDetails(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id){
        userDetailsService.deleteUserDetails(id);
        return ResponseEntity.ok().build();
    }
}
