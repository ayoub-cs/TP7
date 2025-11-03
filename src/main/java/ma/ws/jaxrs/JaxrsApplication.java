package ma.ws.jaxrs;

import ma.ws.jaxrs.entities.Compte;
import ma.ws.jaxrs.entities.TypeCompte;
import ma.ws.jaxrs.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class JaxrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaxrsApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository) {
		return args -> {
			// Création de comptes initiaux
			compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, 15000.0, new Date(), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, 7500.50, new Date(), TypeCompte.EPARGNE));

			// Affichage dans la console
			System.out.println("=== COMPTES INITIALISÉS DANS LA BASE ===");
			compteRepository.findAll().forEach(c -> {
				System.out.println("Compte ID: " + c.getId() +
						" | Solde: " + c.getSolde() +
						" | Type: " + c.getType() +
						" | Date: " + c.getDateCreation());
			});
			System.out.println("=========================================");
		};
	}
}