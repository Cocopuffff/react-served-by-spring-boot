package com.kennethquek.lil.wisdompet.data.repositories;
import com.kennethquek.lil.wisdompet.data.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    CustomerEntity findByEmail(String email);
}
