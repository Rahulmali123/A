package com.becoder.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {

    private Integer id;

    @NotBlank
    private String name;

    @NotEmpty
    @Size(min = 3, max = 10, message = "description min and max size is 3-10")
    private String description;

    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Price must be a valid number with up to two decimal places")
    private String price;
    
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 1000, message = "Quantity must not be more than 1000")
    private Integer quantity;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
}
