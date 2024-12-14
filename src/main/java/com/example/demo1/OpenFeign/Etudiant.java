package com.example.demo1.OpenFeign;

import com.example.demo1.UsersProfiles.ContactPreference;
import com.example.demo1.enumm.DietaryHabit;
import com.example.demo1.enumm.Lifestyle;
import com.example.demo1.enumm.Passion;
import com.example.demo1.enumm.Personality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    private Integer id;
    private String ecole;
    private String numeroTele;
    private String paysCode;
    private Date dateNaissance;
    private String langues;
    private String description;
    private String photoUrl; // Optional, as not every user may want to upload a photo.
    private ContactPreference contactPreference;
    private List<Personality> personalityTraits;  // List of personality traits (from the enum)
    private List<Lifestyle> lifestylePreferences;  // List of lifestyle preferences (from the enum)
    private List<DietaryHabit> dietaryHabits;  // List of dietary habits (from the enum)
    private List<Passion> passions;

}
