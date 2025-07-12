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
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProductJpaTest extends AbstractJpaTest {

    @Autowired
    ProductRepo productRepo;

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
        Optional<ProductEntity> productEntityBeforeUpdateOpt = productRepo.findById(1L);
        assertTrue(productEntityBeforeUpdateOpt.isPresent());
       ProductEntity productEntity = productEntityBeforeUpdateOpt.get();
       productEntity.setName("abhi");

        ProductEntity updatedProduct = productRepo.save(productEntity);

        Optional<ProductEntity> updatedProductOpt = productRepo.findById(updatedProduct.getId());

        assertThat(updatedProductOpt).isPresent();
        assertThat(updatedProductOpt.get())
                .extracting(ProductEntity::getName)
                .isEqualTo("abhi");




    }

}
