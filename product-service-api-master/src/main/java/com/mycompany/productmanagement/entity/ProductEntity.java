package com.mycompany.productmanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "PRODUCT_TABLE")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;
    @Column(name = "PRODUCT_CODE", nullable = false)
    private String productCode;
    @Column(name = "DESCRIPTION")
    private String description;
}
