package com.github.holaspringboot.controller;

import com.github.holaspringboot.dto.BackendDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by artri on 11.06.17.
 */
@RestController
@RequestMapping("/api")
public class GreetingRestController {
    private RestTemplate restTemplate = new RestTemplate();
    private String backendServiceHost = "localhost";
    private int backendServicePort = 8080;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = "text/plain")
    public String greeting() {
        String backendServiceUrl = String.format("http://%s:%d/api/hello?greeting={greeting}",
                backendServiceHost, backendServicePort);
        System.out.println("Sending to: " + backendServiceUrl);

        BackendDTO response = restTemplate.getForObject(backendServiceUrl, BackendDTO.class, "param");
        return String.format("%s at host %s", response.getGreeting(), response.getIp());
    }
}
