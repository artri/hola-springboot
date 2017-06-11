package com.github.holaspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.holaspringboot.dto.BackendDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by artri on 11.06.17.
 */
@RestController
@RequestMapping("/api")
public class HolaRestController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(method = RequestMethod.GET, value = "/hola", produces = "text/plain")
    public String hola() {
        return String.format("Hola Spring Boot from %s", getIp());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "application/json")
    public String hello(@RequestParam("greeting") String greeting) throws Exception {

        BackendDTO response = new BackendDTO();
        response.setGreeting(greeting + " from cluster Backend");
        response.setTime(System.currentTimeMillis());
        response.setIp(getIp());

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
    }

    private String getIp() {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return hostname;
    }
}
