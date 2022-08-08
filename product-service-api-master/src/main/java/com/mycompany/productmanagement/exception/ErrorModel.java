package com.mycompany.productmanagement.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ErrorModel implements Serializable {
    private String code;
    private String message;
}
