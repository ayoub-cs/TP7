package ma.ws.jaxrs.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.ws.jaxrs.entities.Compte;
import ma.ws.jaxrs.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  // OBLIGATOIRE : Spring crée le bean
@Path("/banque")
public class CompteRestJaxRSAPI {

    @Autowired
    private CompteRepository compteRepository;  // Injecté par Spring

    // ==================== GET ALL ====================
    @GET
    @Path("/comptes")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }

    // ==================== GET BY ID ====================
    @GET
    @Path("/comptes/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCompte(@PathParam("id") Long id) {
        return compteRepository.findById(id)
                .map(compte -> Response.ok(compte).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // ==================== CREATE ====================
    @POST
    @Path("/comptes")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCompte(Compte compte) {
        Compte saved = compteRepository.save(compte);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    // ==================== UPDATE ====================
    @PUT
    @Path("/comptes/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCompte(@PathParam("id") Long id, Compte updatedCompte) {
        return compteRepository.findById(id)
                .map(existing -> {
                    existing.setSolde(updatedCompte.getSolde());
                    existing.setDateCreation(updatedCompte.getDateCreation());
                    existing.setType(updatedCompte.getType());
                    Compte saved = compteRepository.save(existing);
                    return Response.ok(saved).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // ==================== DELETE ====================
    @DELETE
    @Path("/comptes/{id}")
    public Response deleteCompte(@PathParam("id") Long id) {
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}