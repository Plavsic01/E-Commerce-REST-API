package com.plavsic.ecommerce.generic.controller;

import com.plavsic.ecommerce.generic.service.AbstractService;
import com.plavsic.ecommerce.security.AuthUserDetails;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class GenericController<T,U> {

    private final AbstractService<T,U> service;

    public GenericController(AbstractService<T, U> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> find(@PathVariable Long id, @AuthenticationPrincipal AuthUserDetails userDetails) {
        T obj = service.findById(id,userDetails);
        if(obj == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "size",defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response = service.findAll(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<T> create(@Valid @RequestBody T entity) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        T obj = service.save(entity);
        return new ResponseEntity<>(obj,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id,
                                    @RequestBody T entity,
                                    @AuthenticationPrincipal AuthUserDetails userDetails)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        T obj = service.update(id, entity,userDetails);
        if(obj == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<T> delete(@PathVariable Long id,@AuthenticationPrincipal AuthUserDetails userDetails) {
        if(service.delete(id,userDetails)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
