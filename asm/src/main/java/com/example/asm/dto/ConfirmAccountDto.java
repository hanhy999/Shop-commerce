package com.example.asm.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmAccountDto {
    @NotEmpty
    private String password;
    @NotEmpty
    private String code;
}
