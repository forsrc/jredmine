package com.forsrc.jredmine.server.controller;

import java.io.Serializable;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.forsrc.jredmine.server.service.BaseService;

import io.jsonwebtoken.lang.Assert;


@PreAuthorize("hasRole('ADMIN')")
public abstract class BaseController<T extends Serializable, PK> {

    public abstract BaseService<T, PK> getBaseService();


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{pk}")
    public ResponseEntity<T> get(@PathVariable("pk") PK pk) {
        T t = null;
        try {
            t = getBaseService().get(pk);
            LOGGER.info("--> {} : {}", pk, t.toString());
        } catch (EntityNotFoundException | NestedRuntimeException e) {
            LOGGER.warn("--> {} : {}", pk, e.getMessage());
            return new ResponseEntity<>(t, HttpStatus.OK);
        }

        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<T>> get(@RequestParam(name = "page", required = false) Integer page,
                                       @RequestParam(name = "size", required = false) Integer size) {
        page = page == null || page == 0 ? 0 : page;
        size = size == null || size == 0 ? 10 : size;
        size = size >= 1000 ? 1000 : size;
        Page<T> list = getBaseService().page(page, size);
        LOGGER.info("--> get({}, {}) : {}", page, size, list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<T> save(@RequestBody T t) {
        Assert.notNull(t, "save: Object is null");
        //Assert.notNull(t, "save: username is nul");
        t = getBaseService().save(t);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T t) {
        Assert.notNull(t, "update: Object is null");
        //Assert.notNull(user.getUsername(), "save: username is nul");
        t = getBaseService().update(t);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @DeleteMapping("/{pk}")
    public ResponseEntity<String> delete(@PathVariable("pk") PK pk) {
        Assert.notNull(pk, "delete: pk is nul");
        getBaseService().delete(pk);
        return new ResponseEntity<>(pk.toString() + " is deleted.", HttpStatus.OK);
    }
}