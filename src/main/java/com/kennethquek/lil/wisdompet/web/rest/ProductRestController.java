package com.kennethquek.lil.wisdompet.web.rest;

import com.kennethquek.lil.wisdompet.services.ProductService;
import com.kennethquek.lil.wisdompet.web.errors.BadRequestException;
import com.kennethquek.lil.wisdompet.web.models.Product;
import com.kennethquek.lil.wisdompet.web.models.ProductWithVendorName;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/all")
    public List<ProductWithVendorName> getAllProductWithVendorNames() {
        return this.productService.getAllProductWithVendorName();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return this.productService.createOrUpdate(product);
    }

    @GetMapping("/{id}")
    public Product updateProduct(@PathVariable("id")long id, @RequestBody Product product) {
        if(id != product.getProductId()) {
            throw new BadRequestException("ids do not match");
        }
        return this.productService.createOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteCustomer(@PathVariable("id")long id) {
        this.productService.deleteProduct(id);
    }
}
