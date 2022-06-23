package com.ipiecoles.java.java350.model.repository;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.repository.EmployeRepository;
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
    public void testFindLastMatricule(){

        Employe employe = new Employe("DOE","JOHN","T12345", LocalDate.now(),2500d,1,1.0);
        employeRepository.save(employe);

        String lastMatricule = employeRepository.findLastMatricule();

        Assertions.assertThat(lastMatricule).isEqualTo("12345");

    }

    @BeforeEach
    @AfterEach
    public void purge(){
        employeRepository.deleteAll();
    }

}
