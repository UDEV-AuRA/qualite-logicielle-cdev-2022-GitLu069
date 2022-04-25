package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void testGetNbAnneesAncienneteDateEmbauchePassee() {
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(2));
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(2);
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheFuture() {
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().plusYears(2));
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNull() {
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(null);
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }
}
