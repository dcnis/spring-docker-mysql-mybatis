package de.schmidtdennis.mysqlspring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String greetAdmin(){
        return "I am Admin!!!";
    }

}
