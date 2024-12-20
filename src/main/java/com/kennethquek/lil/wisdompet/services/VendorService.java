package com.kennethquek.lil.wisdompet.services;

import com.kennethquek.lil.wisdompet.data.entities.VendorEntity;
import com.kennethquek.lil.wisdompet.data.repositories.VendorRepository;
import com.kennethquek.lil.wisdompet.web.errors.NotFoundException;
import com.kennethquek.lil.wisdompet.web.models.Vendor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getAllVendor(String filterName) {
        List<Vendor> vendors = new ArrayList<>();
        if (StringUtils.hasLength(filterName)) {
            VendorEntity entity = this.vendorRepository.findByName(filterName);
            vendors.add(this.translateDbToWeb(entity));
        }
        Iterable<VendorEntity> entities = this.vendorRepository.findAll();
        entities.forEach(entity->{
            vendors.add(this.translateDbToWeb(entity));
        });
        return vendors;
    }

    public Vendor getVendor(long id) {
        Optional<VendorEntity> optional = this.vendorRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("vendor not found with id");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Vendor createOrUpdate(Vendor vendor) {
        VendorEntity entity = this.translateWebToDb(vendor);
        entity = this.vendorRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteVendor(long id) {
        this.vendorRepository.deleteById(id);
    }

    private VendorEntity translateWebToDb(Vendor vendor) {
        VendorEntity entity = new VendorEntity();
        entity.setId(vendor.getVendorId() == null? 0 : vendor.getVendorId());
        entity.setPhoneNumber(vendor.getPhoneNumber());
        entity.setAddress(vendor.getAddress());
        entity.setName(vendor.getName());
        entity.setContact(vendor.getContact());
        return entity;
    }

    private Vendor translateDbToWeb(VendorEntity entity) {
        return new Vendor(
                entity.getId(),
                entity.getName(),
                entity.getContact(),
                entity.getPhoneNumber(),
                entity.getEmailAddress(),
                entity.getAddress()
        );
    }
}
