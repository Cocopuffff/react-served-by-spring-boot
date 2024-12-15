package com.kennethquek.lil.wisdompet.data.repositories;

import com.kennethquek.lil.wisdompet.data.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
