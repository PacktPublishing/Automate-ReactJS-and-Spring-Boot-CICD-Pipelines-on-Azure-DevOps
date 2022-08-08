package com.mycompany.productmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductModel {

    private Long id;

    @NotNull(message = "Product name cannot be empty")
    @Size(min = 1, max = 50, message = "Product name should be of 1-50 characters in length")
    private String name;

    @NotNull(message = "mobile number cannot be empty")
    private Double amount;

    private String productCode;
    private String description;

}
