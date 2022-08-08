package com.mycompany.productmanagement.model;

import com.mycompany.productmanagement.entity.ProductEntity;
import com.mycompany.productmanagement.exception.ErrorModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meanbean.test.BeanTester;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ModelEntityTest {

    @Test
    @DisplayName("Testing all models and entities")
    public void test_model_entity(){
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(ProductModel.class);
        beanTester.testBean(ErrorModel.class);
        beanTester.testBean(ProductEntity.class);
    }
}
