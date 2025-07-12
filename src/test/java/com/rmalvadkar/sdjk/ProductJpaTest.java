package com.rmalvadkar.sdjk;

import com.rmalvadkar.sdjk.common.AbstractJpaTest;
import com.rmalvadkar.sdjk.entities.ProductEntity;
import com.rmalvadkar.sdjk.repositories.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class ProductJpaTest {

    @BeforeEach
    void setUp() {
        productRepo.deleteAllInBatch();
    }

    @Autowired
    ProductRepo productRepo;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Test
    void should_create_bew_product() {
        ProductEntity newProductEntity = createNewProduct();

        Optional<ProductEntity> productEntityOpt = productRepo.findById(newProductEntity.getId());
        assertThat(productEntityOpt).isPresent();
        assertThat(productEntityOpt.get())
                .extracting(ProductEntity::getName)
                .isEqualTo("rushilesh");


    }

    private ProductEntity createNewProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("rushilesh");
        ProductEntity savedProductEntity = productRepo.save(productEntity);
        return savedProductEntity;
    }

    @Test
    void should_update_product(){
        //  status -> {};
        ProductEntity savedProductEntity = transactionTemplate.execute(status -> {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName("rushilesh");
            return productRepo.save(productEntity);
        });

        ProductEntity updatedProduct = transactionTemplate.execute(status -> {
            Optional<ProductEntity> productOpt = productRepo.findById(savedProductEntity.getId());
            assertThat(productOpt).isPresent();
            ProductEntity productPresent = productOpt.get();
            productPresent.setName("abhi");
            return productRepo.save(productPresent);
        });

        assertThat(updatedProduct.getName()).isEqualTo("abhi");


    }

    @Test
    void should_get_the_product_sorting() {

        List<ProductEntity> products = transactionTemplate.execute(status -> {
            ProductEntity sachin = ProductEntity.of("sachin");
            ProductEntity ravi = ProductEntity.of("ravi");
            ProductEntity abhi = ProductEntity.of("abhi");
            List<ProductEntity> productList = List.of(sachin, ravi, abhi);
            productRepo.saveAll(productList);
            return productRepo.findAll(Sort.by(Sort.Direction.DESC, "name"));

        });
        assertThat(products).hasSize(3);
        assertThat(products.getFirst().getName()).isEqualTo("sachin");


    }
}
