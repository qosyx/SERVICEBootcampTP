/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.controllers;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.bootcamp.Repository.BenefciaireRepository;
import com.bootcamp.entity.Beneficiaire;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;

/**
 *
 * @author Archange
 */
@Path("/beneficiaires")
public class BeneficiaireRessource {

    BenefciaireRepository beneficiaireRepository = new BenefciaireRepository("PostgresPuWeb");

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Beneficiaire> getList() throws SQLException {

        List<Beneficiaire> donnees = beneficiaireRepository.findAll();
        return donnees;
//        if (donnees != null) {
//            //String jsonString = gson.toJson(donnees);
//            return Response.status(200).entity(donnees).build();
//        } else {
//            return Response.status(404).entity(donnees).build();
//        }
    }

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addBeneficiaire(Beneficiaire beneficiaire) {
        String output = " Nouveau beneficiaire créé : \n";
        try {
            beneficiaireRepository.create(beneficiaire);
            return Response.status(200).entity(output + beneficiaire.getId() + "\n"
                    + beneficiaire.getNom() + "\n" + beneficiaire.getDescritption()).build();
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Beneficiaire non créé").build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateBeneficiaire(Beneficiaire beneficiaire) {
        String output = " Mise à jour de Beneficiaire effectuée avec succès pour : ";
        try {
            beneficiaireRepository.create(beneficiaire);
            return Response.status(200).entity(output + beneficiaire.getId() + "\n"
                    + beneficiaire.getNom() + "\n" + beneficiaire.getDescritption()).build();
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Beneficiaire non créé").build();
        }
    }

}
