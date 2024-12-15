package com.kennethquek.lil.wisdompet.services;

import com.kennethquek.lil.wisdompet.data.entities.ServicesEntity;
import com.kennethquek.lil.wisdompet.data.repositories.ServicesRepository;
import com.kennethquek.lil.wisdompet.web.errors.NotFoundException;
import com.kennethquek.lil.wisdompet.web.models.Services;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public List<Services> getAllServices(String filterServices) {
        List<Services> services = new ArrayList<>();
        if (StringUtils.hasLength(filterServices)) {
            ServicesEntity entity = this.servicesRepository.findByName(filterServices);
            services.add(this.translateDbToWeb(entity));
        }
        Iterable<ServicesEntity> entities = this.servicesRepository.findAll();
        entities.forEach(entity->{
            services.add(this.translateDbToWeb(entity));
        });
        return services;
    }

    public Services getService(long id) {
        Optional<ServicesEntity> optional = this.servicesRepository.findById(id);
        if (optional.isEmpty()){
            throw new NotFoundException("service not found with id");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Services createOrUpdateService(Services service) {
        ServicesEntity entity = this.translateWebToDb(service);
        entity = this.servicesRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteService(long id) {
        this.servicesRepository.deleteById(id);
    }

    private ServicesEntity translateWebToDb(Services services) {
        ServicesEntity entity = new ServicesEntity();
        entity.setServiceId(services.getServiceId());
        entity.setName(services.getName());
        entity.setPrice(services.getPrice());
        return entity;
    }

    private Services translateDbToWeb(ServicesEntity entity) {
        return new Services(entity.getServiceId(), entity.getName(), entity.getPrice());
    }
}
