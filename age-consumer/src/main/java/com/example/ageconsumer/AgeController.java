package com.example.ageconsumer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgeController {

    private AgeService ageService;

    public AgeController(AgeService ageService) {
        this.ageService = ageService;
    }

    @GetMapping("/age-calculate")
    public ResponseEntity calculateAge(@RequestParam(name = "birthDate") String birthDate) {
        AgeResponse ageResponse = ageService.calculateAge(birthDate);
        if (ageResponse != null) {
            return ResponseEntity.ok().body(ageResponse);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
