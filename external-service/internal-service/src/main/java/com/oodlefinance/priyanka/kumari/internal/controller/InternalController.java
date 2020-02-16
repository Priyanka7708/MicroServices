/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oodlefinance.priyanka.kumari.internal.controller;

import com.oodlefinance.priyanka.kumari.internal.models.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author priyankak
 */
@RestController
@RequestMapping("/message")
public class InternalController {

    static HashMap<Integer, String> messages = new HashMap<>();

    @RequestMapping("/{id}")
    public List<Message> getMessageForId(@PathVariable("id") Integer id) {
        List<Message> messageList = new ArrayList<>();
        messages.entrySet().forEach((mapElement) -> {
            int val = (int) mapElement.getKey();
            if (val == id) {
                messageList.add(new Message(id, (String) mapElement.getValue()));
            }
        });
        return messageList;
    }

    @RequestMapping("")
    public List<Message> getMessages() {
        List<Message> messageList = new ArrayList<>();
        messages.entrySet().stream().map((mapElement) -> new Message((int) mapElement.getKey(), (String) mapElement.getValue())).forEachOrdered((message) -> {
            messageList.add(message);
        });

        return messageList;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public List<Message> addMessages(@RequestBody String message) {
        // find the max id value in the map
        int max = 0;
        for (Map.Entry mapElement : messages.entrySet()) {
            int val = (int) mapElement.getKey();
            if (val > max) {
                max = val;
            }
        }
        messages.put(max + 1, message);
        return Collections.singletonList(new Message(max + 1, message));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteMessage(@PathVariable("id") Integer id
    ) {
        Set<Entry<Integer, String>> entrySet = messages.entrySet();

        // Collection Iterator - to avoid concurrent modification exception
        Iterator<Entry<Integer, String>> itr = entrySet.iterator();
        while (itr.hasNext()) {
            Entry<Integer, String> entry = itr.next();
            int key = entry.getKey();

            if (key == id) {
                // try to remove, while iterating
                itr.remove();
            }
        }
        return "Id " + id + " successfully deleted";
    }
}
