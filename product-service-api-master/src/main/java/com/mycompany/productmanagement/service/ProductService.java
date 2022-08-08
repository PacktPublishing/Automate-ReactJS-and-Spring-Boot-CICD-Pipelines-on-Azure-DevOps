package com.mycompany.productmanagement.service;

import com.mycompany.productmanagement.exception.BusinessException;
import com.mycompany.productmanagement.model.ProductModel;

import java.util.List;

public interface ProductService {
    ProductModel saveProduct(ProductModel productModel) throws BusinessException;
    ProductModel updateProduct(ProductModel productModel, Long productId) throws BusinessException;
    ProductModel updateProductPrice(Double newPrice, Long productId) throws BusinessException;
    ProductModel deleteProduct(Long productId) throws BusinessException;
    ProductModel productDetail(Long productId) throws BusinessException;
    List<ProductModel> getAllProducts() throws BusinessException;
}
