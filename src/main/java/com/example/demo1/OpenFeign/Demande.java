package com.example.demo1.OpenFeign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Demande {
    private Integer id;
    private String ville;
    private Integer budgetMensuel;
    private String sexe;
    private String name;
    private String ecole;
    private Boolean possedeDejaLocal;
//    private String typeChambre; // Add typeChambre as a String for readability



    private Integer etudiantId;
}
