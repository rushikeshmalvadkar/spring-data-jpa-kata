package com.rmalvadkar.sdjk.repositories;

import com.rmalvadkar.sdjk.entities.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends Repository<ProductEntity, Long> {

     ProductEntity save(ProductEntity entity);

     Optional<ProductEntity> findById(Long id);

     void deleteAllInBatch();

     List<ProductEntity> saveAll(Iterable<ProductEntity> entities);

     List<ProductEntity> findAll(Sort sort);
}
