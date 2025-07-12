package com.rmalvadkar.sdjk.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;



    public ProductEntity(String name){
        this.name= name;
    }

    public static ProductEntity of(String name){
       return  new ProductEntity(name);
    }

}
