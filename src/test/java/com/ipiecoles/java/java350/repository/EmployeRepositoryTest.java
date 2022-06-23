package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.model.Employe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class EmployeRepositoryTest {

    @Autowired
    EmployeRepository employeRepository;

    @Test
    public void testFindLastMatriculeWith1Employe(){
        //Given
        Employe employe = new Employe("Doe", "John", "T12345",
                LocalDate.now(), 2500d, 1, 1.0);
        employeRepository.save(employe);

        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("12345");
    }

    @Test
    public void testFindLastMatriculeWith3Employes(){
        //Given
        Employe employe1 = new Employe("Doe", "John", "T02345",
                LocalDate.now(), 2500d, 1, 1.0);
        employeRepository.save(employe1);
        Employe employe2 = new Employe("Doe", "John", "C02346",
                LocalDate.now(), 2500d, 1, 1.0);
        employeRepository.save(employe2);
        Employe employe3 = new Employe("Doe", "John", "C02347",
                LocalDate.now(), 2500d, 1, 1.0);
        employeRepository.save(employe3);

        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("02347");
    }

    @Test
    public void testFindLastMatriculeWith0Employe(){
        //Given

        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertThat(lastMatricule).isNull();
    }

    @BeforeEach
    @AfterEach
    public void purge(){
        employeRepository.deleteAll();
    }

}
