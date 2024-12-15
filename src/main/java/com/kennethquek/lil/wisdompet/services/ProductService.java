package com.kennethquek.lil.wisdompet.services;

import com.kennethquek.lil.wisdompet.data.entities.ProductEntity;
import com.kennethquek.lil.wisdompet.data.repositories.ProductRepository;
import com.kennethquek.lil.wisdompet.services.VendorService;
import com.kennethquek.lil.wisdompet.web.errors.NotFoundException;
import com.kennethquek.lil.wisdompet.web.models.Customer;
import com.kennethquek.lil.wisdompet.web.models.Product;
import com.kennethquek.lil.wisdompet.web.models.ProductWithVendorName;
import com.kennethquek.lil.wisdompet.web.models.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final VendorService vendorService;

    public ProductService(ProductRepository productRepository, VendorService vendorService) {
        this.productRepository = productRepository;
        this.vendorService = vendorService;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Iterable<ProductEntity> entities = this.productRepository.findAll();
        entities.forEach(entity->{
            products.add(this.translateDbToWeb(entity));
        });

        return products;
    }

    public List<ProductWithVendorName> getAllProductWithVendorName() {
        List<ProductWithVendorName> productWithVendorNameList = new ArrayList<>();
        Iterable<ProductEntity> entities = this.productRepository.findAll();
        entities.forEach(entity -> {
            Product product = this.translateDbToWeb(entity);
            Long vendorId = product.getVendorId();
            String vendorName = vendorService.getVendor(vendorId).getName();
            ProductWithVendorName productWithVendorName = new ProductWithVendorName(product, vendorName);
            productWithVendorNameList.add(productWithVendorName);
        });

        return productWithVendorNameList;
    }

    public Product getProduct(long id) {
        Optional<ProductEntity> optional = this.productRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("product not found with id");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Product createOrUpdate(Product product) {
        ProductEntity entity = this.translateWebToDb(product);
        entity = this.productRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public ProductEntity translateWebToDb(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getProductId() == null? 0 : product.getProductId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setVendorId(product.getVendorId());
        return entity;
    }

    public Product translateDbToWeb(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getVendorId());
    }
}
