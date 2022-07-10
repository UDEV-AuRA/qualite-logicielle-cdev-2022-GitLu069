package com.ipiecoles.java.java350.model;

import com.ipiecoles.java.java350.exception.EmployeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.time.LocalDate;

public class EmployeTest {
    @Test
    public void testGetNombreAnneeAncienneteDateEmbaucheNow(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @Test
    public void testGetNombreAnneeAncienneteDateEmbauchePassee(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(2));

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(2);
    }

    @Test
    public void testGetNombreAnneeAncienneteDateEmbaucheFuture(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().plusYears(2));

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @Test
    public void testGetNombreAnneeAncienneteDateEmbaucheNull(){
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
            "'M12345', 0, 1, 1.0, 1700.0",
            "'M12345', 2, 1, 1.0, 1900.0",
            "'J12345', 0, , 1.0, 1000.0",
            "'J12345', 0, 1, 1.0, 1000.0",
            ", 0, 1, 1.0, 1000.0",
            "'J12345', 0, 0, 2.0, 600.0",
            "'J12345', 0, 2, 1.0, 2300.0"
    })
    public void testGetPrimeAnnuelle(String matricule, Integer nbAnneesAnciennete, Integer performance, Double tauxTravail, Double primeCalculee){
        //Given
        Employe employe = new Employe("Lucas", "Vincent", matricule, LocalDate.now().minusYears(nbAnneesAnciennete), 2500d, performance, tauxTravail);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(primeCalculee);
    }

    @Test
    public void testAugmenterSalaire() throws EmployeException{
        //Given
        Employe employe = new Employe("Lucas", "Vincent", "T1234", LocalDate.now(), 2000d, 1, 1.0);

        //When
        employe.augmenterSalaire(20d);

        //Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2400);
    }

    @Test
    public void testAugmenterSalaireVirgule() throws EmployeException{
        //Given
        Employe employe = new Employe("Lucas", "Vincent", "T1234", LocalDate.now(), 1824d, 1, 1.0);

        //When
        employe.augmenterSalaire(22.5d);

        //Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2234.4);
    }

    @Test
    public void testAugmenterSalaireNull(){
        //Given
        Employe employe = new Employe("Lucas", "Vincent", "T1234", LocalDate.now(), null, 1, 1.0);

        //When
        Throwable e = Assertions.catchThrowable(() -> {
            employe.augmenterSalaire(20);
        });

        //Then
        Assertions.assertThat(e).isInstanceOf(EmployeException.class).hasMessage("Aucun salaire est passé en paramètre");
    }

    @Test
    public void testAugmenterSalaireNegatif(){
        //Given
        Employe employe = new Employe("Lucas", "Vincent", "T1234", LocalDate.now(), -1800d, 1, 1.0);

        //When
        Throwable e = Assertions.catchThrowable(() -> {
            employe.augmenterSalaire(20);
        });

        //Then
        Assertions.assertThat(e).isInstanceOf(EmployeException.class).hasMessage("Le salaire entré est incorrect");
    }

    @ParameterizedTest
    @CsvSource({
            "2019, 8",
            "2021, 10",
            "2022, 10",
            "2032, 11",
    })
    public void testGetNbRtt(Integer year, Integer nbRttCalcule){
        //Given
        Employe employe = new Employe();

        //When
        Integer nbRtt = employe.getNbRtt(LocalDate.of(year,1,1));

        //Then
        Assertions.assertThat(nbRtt).isEqualTo(nbRttCalcule);
    }
}