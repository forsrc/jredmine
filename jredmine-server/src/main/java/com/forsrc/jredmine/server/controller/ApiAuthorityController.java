package com.forsrc.jredmine.server.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forsrc.jredmine.server.model.Authority;
import com.forsrc.jredmine.server.service.AuthorityService;

@RestController
@RequestMapping("/api/authority")
public class ApiAuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/{pk}")
    public ResponseEntity<List<Authority>> get(@PathVariable("pk") String usanename) {

        List<Authority> list = null;
        try {
            list = authorityService.getByUsername(usanename);

        } catch (EntityNotFoundException | NestedRuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}