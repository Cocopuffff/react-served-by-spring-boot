package com.kennethquek.lil.wisdompet.web.rest;

import com.kennethquek.lil.wisdompet.services.VendorService;
import com.kennethquek.lil.wisdompet.web.errors.BadRequestException;
import com.kennethquek.lil.wisdompet.web.models.Vendor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vendors")
public class VendorRestController {

    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<Vendor> getAll(@RequestParam(name="name", required = false)String name) {
        return this.vendorService.getAllVendor(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return this.vendorService.createOrUpdate(vendor);
    }

    @GetMapping("/{id}")
    public Vendor getVendor(@PathVariable("id")long id) {
        return this.vendorService.getVendor(id);
    }

    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable("id")long id, @RequestBody Vendor vendor) {
        if (id != vendor.getVendorId()) {
            throw new BadRequestException("ids do not match");
        }
        return this.vendorService.createOrUpdate(vendor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteVendor(@PathVariable("id")long id) {
        this.vendorService.deleteVendor(id);
    }

}