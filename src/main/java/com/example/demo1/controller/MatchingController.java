package com.example.demo1.controller;

import com.example.demo1.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    /**
     * Endpoint to calculate the matching score between a user and a demande.
     *
     * @param userId The ID of the user.
     * @param demandeId The ID of the demande.
     * @return The matching score as a percentage.
     */
    @GetMapping("/matching/score")
    public double calculateMatchingScore(@RequestParam("userId") int userId, @RequestParam("demandeId") int demandeId) {
        return matchingService.calculateMatchingScore(userId, demandeId);
    }
}
