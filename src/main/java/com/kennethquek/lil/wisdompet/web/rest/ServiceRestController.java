package com.kennethquek.lil.wisdompet.web.rest;

import com.kennethquek.lil.wisdompet.services.ServicesService;
import com.kennethquek.lil.wisdompet.web.errors.BadRequestException;
import com.kennethquek.lil.wisdompet.web.models.Services;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/services")
public class ServiceRestController {

    private final ServicesService servicesService;

    public ServiceRestController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping
    public List<Services> getAll(@RequestParam(name="name", required = false) String name) {
        return this.servicesService.getAllServices(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Services createService(@RequestBody Services services) {
        return this.servicesService.createOrUpdateService(services);
    }

    @GetMapping("/{id}")
    public Services getService(@PathVariable("id")long id) {
        return this.servicesService.getService(id);
    }

    @PutMapping("/{id}")
    public Services updateService(@PathVariable("id")long id, @RequestBody Services services) {
        if (id != services.getServiceId()) {
            throw new BadRequestException("ids do not match");
        }
        return this.servicesService.createOrUpdateService(services);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteService(@PathVariable("id") long id) {
        this.servicesService.deleteService(id);
    }
}
