package com.github.erfan_davoodi_nasr_hw10_maktab117.model.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
public class SaveUserRequest {
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Size(min = 11, max = 11, message = "Phone number must contain exactly 11 digits")
    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

}
