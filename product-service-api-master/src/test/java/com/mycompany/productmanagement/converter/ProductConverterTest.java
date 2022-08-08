package com.mycompany.productmanagement.converter;

import com.mycompany.productmanagement.entity.ProductEntity;
import com.mycompany.productmanagement.model.ProductModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductConverterTest {

    @InjectMocks
    private ProductConverter productConverter;

    @Test
    public void test_convertModelToEntity(){

        ProductModel productModel = new ProductModel();
        productModel.setName("Cell phone");
        productModel.setProductCode("SKQ#1234");
        productModel.setAmount(89898.89);
        productModel.setDescription("Dummy product description");

        ProductEntity productEntity = productConverter.convertModelToEntity(productModel);

        assertEquals(productModel.getName(), productEntity.getName());
        assertEquals(productModel.getAmount(), productEntity.getAmount());
        assertEquals(productModel.getProductCode(), productEntity.getProductCode());
        assertEquals(productModel.getDescription(), productEntity.getDescription());
    }
}
