package com.mycompany.productmanagement.controller;

import com.mycompany.productmanagement.exception.BusinessException;
import com.mycompany.productmanagement.model.ProductModel;
import com.mycompany.productmanagement.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @PostMapping(name = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> saveProduct(@Valid @RequestBody ProductModel productModel) throws BusinessException {
        logger.debug("Entering method saveProduct");
        ProductModel model = productService.saveProduct(productModel);
        logger.debug("Exiting method saveProduct");
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() throws BusinessException {
        List<ProductModel> productModelList = productService.getAllProducts();
        return new ResponseEntity<>(productModelList, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductModel> getProductDetail(@PathVariable(name = "id") Long productId) throws BusinessException {

        logger.debug("Entering method getProductDetail");
        ProductModel result = productService.productDetail(productId);
        ResponseEntity<ProductModel> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        logger.debug("Exiting method getProductDetail");
        return responseEntity;
    }

    @PatchMapping("/products/{id}/update-price")
    public ResponseEntity<ProductModel> updateProductPrice(@PathVariable Long id, @RequestBody ProductModel productModel) throws BusinessException {

        ProductModel result = productService.updateProductPrice(productModel.getAmount(), id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody ProductModel productModel) throws BusinessException {

        ProductModel result = productService.updateProduct(productModel, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductModel> deleteProduct(@PathVariable Long id) throws BusinessException {

        ProductModel model = productService.deleteProduct(id);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
