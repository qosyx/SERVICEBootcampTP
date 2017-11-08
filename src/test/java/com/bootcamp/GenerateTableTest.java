package com.bootcamp;

import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.bootcamp.entity.Beneficiaire;
import com.bootcamp.Repository.BenefciaireRepository;
import java.sql.SQLException;
import org.testng.annotations.Test;

public class GenerateTableTest {

    String persistOne = "PostgresPuWeb"; //monPuWeb";

//
    @Test
    public void generateTables() {
//        Persistence.("tpJpa", new Properties());
        Persistence.createEntityManagerFactory(persistOne, new Properties() {
        });

    }

    @Test
    //Ajout de Bénéficiaires
    public void ajoutBeneficiaire() throws SQLException {

        Beneficiaire benef1 = new Beneficiaire(1, "toto", "momo");
        Beneficiaire benef2 = new Beneficiaire(2, "toto", "momo");
        Beneficiaire benef3 = new Beneficiaire(3, "toto", "momo");
        BenefciaireRepository br1 = new BenefciaireRepository(persistOne);
        br1.create(benef1);
        br1.create(benef2);
        br1.create(benef3);

    }
}
