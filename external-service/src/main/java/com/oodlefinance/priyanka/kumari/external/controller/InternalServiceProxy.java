/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oodlefinance.priyanka.kumari.external.controller;

import com.oodlefinance.priyanka.kumari.external.models.Message;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author priyankak
 */
@FeignClient(name = "internal-service", url = "${internal-service.application.url}")
public interface InternalServiceProxy {

    @RequestMapping("/message/{id}")
    public List<Message> getMessageForId(@PathVariable("id") Integer id);

    @RequestMapping("/message")
    public List<Message> getMessages();

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public List<Message> addMessages(@RequestBody String message);

    @RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
    public String deleteMessage(@PathVariable("id") Integer id
    );
}
