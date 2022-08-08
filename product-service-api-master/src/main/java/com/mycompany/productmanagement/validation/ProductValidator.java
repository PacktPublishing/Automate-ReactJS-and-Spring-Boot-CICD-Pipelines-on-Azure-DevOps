package com.mycompany.productmanagement.validation;

import com.mycompany.productmanagement.constant.ErrorType;
import com.mycompany.productmanagement.exception.ErrorModel;
import com.mycompany.productmanagement.model.ProductModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public List<ErrorModel> validateRequest(ProductModel productModel){

        List<ErrorModel> errorModelList = new ArrayList<>();

        if(null != productModel && productModel.getName() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Name cannot be empty");

            errorModelList.add(errorModel);
        }

        if(null != productModel && productModel.getAmount() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Amount cannot be empty");

            errorModelList.add(errorModel);
        }
        return errorModelList;
    }
}
