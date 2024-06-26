package com.mugishap.springboot.v1.dtos;


import com.mugishap.springboot.v1.enums.EGender;
import com.mugishap.springboot.v1.enums.ERole;
import com.mugishap.springboot.v1.security.ValidPassword;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SignUpDTO {

    @Email
    private  String email;

    @NotBlank
    private  String firstName;

    @NotBlank
    private  String lastName;

    @NotBlank
    private  String mobile;

    private EGender gender;

    private ERole role;

    @ValidPassword
    private  String password;
}
