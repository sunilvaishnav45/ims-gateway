package apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Gateway {

    @GetMapping("/test")
    public String getTestString(){
        return "API Gateway up and running";
    }
}
