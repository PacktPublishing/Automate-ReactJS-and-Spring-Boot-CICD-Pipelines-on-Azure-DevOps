package com.mycompany.productmanagement.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusinessException extends Exception {

    private final transient List<ErrorModel> errorList;

    public BusinessException(List<ErrorModel> errorList){
        this.errorList = errorList;
    }
}
