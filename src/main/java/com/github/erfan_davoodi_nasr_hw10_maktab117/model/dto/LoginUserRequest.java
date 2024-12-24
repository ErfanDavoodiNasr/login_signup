package com.github.erfan_davoodi_nasr_hw10_maktab117.model.dto;


import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
public class LoginUserRequest {
    @Size(min = 11, max = 11, message = "Phone number must contain exactly 11 digits")
    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;
}
