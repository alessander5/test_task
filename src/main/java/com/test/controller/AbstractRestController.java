package com.test.controller;

import com.test.domain.AbstractEntity;
import com.test.service.SimpleRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;

public abstract class AbstractRestController<E extends AbstractEntity, S extends SimpleRestService> {

    @Autowired
    private S service;

    @RequestMapping(path={"", "/"}, method = GET)
    public Iterable getAllItems(){
        return service.getAllItems();
    }

    @RequestMapping(path={"", "/"}, method = DELETE)
    public ResponseEntity removeItemById(@RequestParam long id){
        service.removeItemById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path={"", "/"}, method = {POST, PUT})
    public ResponseEntity createOrUpdateItem(@RequestBody E item){
        service.createOrUpdateItem(item);
        return ResponseEntity.ok().build();
    }

}
