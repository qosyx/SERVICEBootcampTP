/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.Repository;

import com.bootcamp.entity.Beneficiaire;

/**
 *
 * @author Archange
 */
public class BenefciaireRepository extends BaseRepository<Beneficiaire>{

    public BenefciaireRepository(String persistUnit) {
        super(persistUnit, Beneficiaire.class);
    }
    
}
