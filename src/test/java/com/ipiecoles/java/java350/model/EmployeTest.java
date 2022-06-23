package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNow(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();

    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbauchePassee(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(2));

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(2);
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheFuture(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().plusYears(2));

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNull(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(null);

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @ParameterizedTest
    @CsvSource({
            "'M12345',0,1,1.0,1700.0",
            "'M12345',2,1,1.0,1900.0",
            "'M12345',0,1,1.0,1700.0",
            ",0,1,1.0,1000.0",
            "'T12345',0,1,1.0,1000.0",
            "'T12345',1,1,1.0,1100.0",
            "'T12345',0,,1.0,1000.0",
            "'T12345',0,2,1.0,2300.0",
            "'T12345',3,2,1.0,2600.0",
            "'T12345',3,2,0.5,1300.0",
    })
    public void testGetPrimeAnnuelle(String matricule,
                                     Integer nbAnneesAnciennete,
                                     Integer performance,
                                     Double tauxTravail,
                                     Double primeCalculee){
        //Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbAnneesAnciennete),
                2500d, performance, tauxTravail);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(primeCalculee);
    }

}
