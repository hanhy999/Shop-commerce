package com.example.asm.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginDto {
    @NotEmpty
    private String username;
    @NotEmpty
    // @Size(min = 3,max = 15)
    private String password;
}
