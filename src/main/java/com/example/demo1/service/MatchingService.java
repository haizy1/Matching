package com.example.demo1.service;

import com.example.demo1.OpenFeign.Demande;
import com.example.demo1.OpenFeign.Etudiant;
import com.example.demo1.OpenFeign.DemandeServiceClient;
import com.example.demo1.OpenFeign.EtudiantServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class MatchingService {

    @Autowired
    private DemandeServiceClient demandeServiceClient;

    @Autowired
    private EtudiantServiceClient etudiantServiceClient;

    /**
     * Calculate the matching score between a user and a demande.
     *
     * @param userId The ID of the Etudiant (user).
     * @param demandeId The ID of the Demande.
     * @return The matching score as a percentage.
     */
    public int calculateMatchingScore(int userId, int demandeId) {
        // Retrieve the current user's Etudiant object
        Etudiant currentUser = etudiantServiceClient.getEtudiantById(userId);
        if (currentUser == null) {
            throw new IllegalArgumentException("Étudiant with ID " + userId + " not found.");
        }

        // Retrieve the Demande object
        Demande demande = demandeServiceClient.getDemandeById(demandeId);
        if (demande == null) {
            throw new IllegalArgumentException("Demande with ID " + demandeId + " not found.");
        }

        // Fetch the Etudiant associated with the Demande
        Etudiant demandeOwner = etudiantServiceClient.getEtudiantById(demande.getEtudiantId());
        if (demandeOwner == null) {
            throw new IllegalArgumentException("Étudiant associated with Demande ID " + demandeId + " not found.");
        }

        // Gather choices for both users
        Set<String> currentUserChoices = gatherChoices(currentUser);
        Set<String> demandeOwnerChoices = gatherChoices(demandeOwner);

        // Calculate and return the matching percentage
        return calculateMatchingPercentage(currentUserChoices, demandeOwnerChoices);
    }

    private Set<String> gatherChoices(Etudiant etudiant) {
        Set<String> choices = new HashSet<>();

        // Add personality traits
        if (etudiant.getPersonalityTraits() != null) {
            etudiant.getPersonalityTraits().forEach(trait -> choices.add("Personality: " + trait.name()));
        }

        // Add lifestyle preferences
        if (etudiant.getLifestylePreferences() != null) {
            etudiant.getLifestylePreferences().forEach(style -> choices.add("Lifestyle: " + style.name()));
        }

        // Add dietary habits
        if (etudiant.getDietaryHabits() != null) {
            etudiant.getDietaryHabits().forEach(habit -> choices.add("DietaryHabit: " + habit.name()));
        }

        // Add passions
        if (etudiant.getPassions() != null) {
            etudiant.getPassions().forEach(passion -> choices.add("Passion: " + passion.name()));
        }

        return choices;
    }

    private int calculateMatchingPercentage(Set<String> currentUserChoices, Set<String> otherUserChoices) {
        if (currentUserChoices.isEmpty() || otherUserChoices.isEmpty()) {
            return 0; // No match if any of the sets is empty
        }

        // Find the intersection of the two sets
        Set<String> commonChoices = new HashSet<>(currentUserChoices);
        commonChoices.retainAll(otherUserChoices); // Keep only common elements

        // Calculate the percentage of matching
        return (int) commonChoices.size() / Math.max(currentUserChoices.size(), otherUserChoices.size()) * 100;
    }
}
