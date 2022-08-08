package com.mycompany.productmanagement.service;

import com.mycompany.productmanagement.constant.ErrorType;
import com.mycompany.productmanagement.converter.ProductConverter;
import com.mycompany.productmanagement.entity.ProductEntity;
import com.mycompany.productmanagement.exception.BusinessException;
import com.mycompany.productmanagement.exception.ErrorModel;
import com.mycompany.productmanagement.model.ProductModel;
import com.mycompany.productmanagement.repository.ProductRepository;
import com.mycompany.productmanagement.validation.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductValidator productValidator;

    @Override
    public ProductModel saveProduct(ProductModel productModel) throws BusinessException {

        //empty check of name and amount
        List<ErrorModel> errorModelList = productValidator.validateRequest(productModel);

        if(!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        //check if product already exist
        ProductEntity ue = productRepository.findByName(productModel.getName());

        if(null != ue){

            List<ErrorModel> errorList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.ALREADY_EXIST.toString());
            errorModel.setMessage("product with this email already exist, try another email");

            errorList.add(errorModel);
            throw new BusinessException(errorList);
        }
        ProductEntity productEntity = null;
        productEntity = productConverter.convertModelToEntity(productModel);
        //BeanUtils.copyProperties(productModel, productEntity);
        productEntity = productRepository.save(productEntity);

        ProductModel model = productConverter.convertEntityToModel(productEntity);

        return model;
    }

    //Full update
    @Override
    public ProductModel updateProduct(ProductModel productModel, Long productId) throws BusinessException {

        Optional<ProductEntity> optEntity = productRepository.findById(productId);

        if(optEntity.isPresent()) {
            ProductEntity pe = optEntity.get();
            pe.setAmount(productModel.getAmount());
            pe.setProductCode(productModel.getProductCode());
            pe.setDescription(productModel.getDescription());
            pe.setName(productModel.getName());
            pe = productRepository.save(pe);
            return productConverter.convertEntityToModel(pe);
        }else{

            List<ErrorModel> errors = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_FOUND.toString());
            errorModel.setMessage("Could not find product with ID = "+productId);
            errors.add(errorModel);

            throw new BusinessException(errors);
        }
    }

    //Partial update
    @Override
    public ProductModel updateProductPrice(Double newPrice, Long productId) throws BusinessException {

        Optional<ProductEntity> optEntity = productRepository.findById(productId);

        if(optEntity.isPresent()) {
            ProductEntity pe = optEntity.get();
            pe.setAmount(newPrice);
            pe = productRepository.save(pe);
            return productConverter.convertEntityToModel(pe);
        }else{

            List<ErrorModel> errors = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_FOUND.toString());
            errorModel.setMessage("Could not find product with ID = "+productId);
            errors.add(errorModel);

            throw new BusinessException(errors);
        }
    }

    @Override
    public ProductModel deleteProduct(Long productId) throws BusinessException {

        Optional<ProductEntity> optEntity = productRepository.findById(productId);

        if(optEntity.isPresent()) {
            productRepository.delete(optEntity.get());
            return productConverter.convertEntityToModel(optEntity.get());
        }else{

            List<ErrorModel> errors = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_FOUND.toString());
            errorModel.setMessage("Could not find product with ID = "+productId);
            errors.add(errorModel);

            throw new BusinessException(errors);
        }
    }

    @Override
    public ProductModel productDetail(Long productId) throws BusinessException {

        Optional<ProductEntity> optEntity = productRepository.findById(productId);

        if(optEntity.isPresent()) {
            return productConverter.convertEntityToModel(optEntity.get());
        }
        return null;
    }

    @Override
    public List<ProductModel> getAllProducts(){

        List<ProductEntity> productList = (List) productRepository.findAll();
        List<ProductModel> productModels = new ArrayList<>();
        ProductModel model = null;

        for(ProductEntity pe :productList){
           model = productConverter.convertEntityToModel(pe);
           productModels.add(model);
        }

        return productModels;
    }
}
