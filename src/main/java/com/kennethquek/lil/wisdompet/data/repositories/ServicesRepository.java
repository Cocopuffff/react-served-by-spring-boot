package com.kennethquek.lil.wisdompet.data.repositories;

import com.kennethquek.lil.wisdompet.data.entities.ServicesEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServicesRepository extends CrudRepository<ServicesEntity, Long> {
    ServicesEntity findByName(String name);
}
