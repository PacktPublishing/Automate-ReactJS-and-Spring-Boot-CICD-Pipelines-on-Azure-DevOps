package com.mycompany.productmanagement.converter;

import com.mycompany.productmanagement.entity.ProductEntity;
import com.mycompany.productmanagement.model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductEntity convertModelToEntity(ProductModel productModel){

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productModel.getName());
        productEntity.setAmount(productModel.getAmount());
        productEntity.setProductCode(productModel.getProductCode());
        productEntity.setDescription(productModel.getDescription());

        return productEntity;
    }

    public ProductModel convertEntityToModel(ProductEntity productEntity){

        ProductModel productModel = new ProductModel();
        productModel.setAmount(productEntity.getAmount());
        productModel.setDescription(productEntity.getDescription());
        productModel.setProductCode(productEntity.getProductCode());
        productModel.setId(productEntity.getId());
        productModel.setName(productEntity.getName());

        return productModel;
    }
}
