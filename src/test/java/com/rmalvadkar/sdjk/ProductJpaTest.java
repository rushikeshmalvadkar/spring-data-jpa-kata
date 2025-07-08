package com.rmalvadkar.sdjk;

import com.rmalvadkar.sdjk.common.AbstractJpaTest;
import com.rmalvadkar.sdjk.entities.ProductEntity;
import com.rmalvadkar.sdjk.repositories.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductJpaTest extends AbstractJpaTest {

    @Autowired
    ProductRepo productRepo;

    @Test
    void should_create_bew_product() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("rushilesh");
        ProductEntity savedProductEntity = productRepo.save(productEntity);

        Optional<ProductEntity> productEntityOpt = productRepo.findById(savedProductEntity.getId());
        assertThat(productEntityOpt).isPresent();
        assertThat(productEntityOpt.get())
                .extracting(ProductEntity::getName)
                .isEqualTo("rushilesh");


    }
}
