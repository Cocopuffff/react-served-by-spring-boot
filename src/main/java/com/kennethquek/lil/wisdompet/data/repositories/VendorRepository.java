package com.kennethquek.lil.wisdompet.data.repositories;

import com.kennethquek.lil.wisdompet.data.entities.VendorEntity;
import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends CrudRepository<VendorEntity, Long> {
    VendorEntity findByName(String name);
}
