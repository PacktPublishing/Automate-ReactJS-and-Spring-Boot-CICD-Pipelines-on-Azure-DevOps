package com.mycompany.productmanagement.repository;

import com.mycompany.productmanagement.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    public ProductEntity findByName(String name);
}
