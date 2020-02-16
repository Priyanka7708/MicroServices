/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oodlefinance.priyanka.kumari.external.controller;

import com.oodlefinance.priyanka.kumari.external.models.Message;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author priyankak
 */
@RestController
@RequestMapping("/message")
public class ExternalController {
    
    @Autowired
    private InternalServiceProxy proxy;
    
    @RequestMapping("/{id}")
    public List<Message> getMessageForId(@PathVariable("id") Integer id) {
        return proxy.getMessageForId(id);
    }
    
    @RequestMapping("")
    public List<Message> getMessages() {
        return proxy.getMessages();
    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public List<Message> addMessages(@RequestBody String message) {
        return proxy.addMessages(message);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteMessage(@PathVariable("id") Integer id) {
        return proxy.deleteMessage(id);
    }
}
