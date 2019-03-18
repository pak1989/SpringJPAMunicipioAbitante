package it.municipioabitantejpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import it.municipioabitantejpa.model.Abitante;
import it.municipioabitantejpa.model.Municipio;
import it.municipioabitantejpa.service.abitante.AbitanteService;
import it.municipioabitantejpa.service.municipio.MunicipioService;

@Component
public class BatteriaDiTestService {

	@Autowired
	private MunicipioService municipioService;

	@Autowired
	private AbitanteService abitanteService;

	// casi di test (usare valorizzando la variabile casoDaTestare nel main)
	public static final String INSERISCI_NUOVO_MUNICIPIO = "INSERISCI_NUOVO_MUNICIPIO";
	public static final String INSERISCI_NUOVO_MUNICIPIO_FAKER_LOOP_20 = "INSERISCI_NUOVO_MUNICIPIO_FAKER_LOOP_20";
	public static final String INSERISCI_ABITANTI_DATO_UN_MUNICIPIO = "INSERISCI_ABITANTI_DATO_UN_MUNICIPIO";
	public static final String INSERISCI_ABITANTI_DATO_UN_MUNICIPIO_FAKER_LOOP_20 = "INSERISCI_ABITANTI_DATO_UN_MUNICIPIO_FAKER_LOOP_20";
	public static final String CERCA_ABITANTE_DATO_ID_MUNICIPIO = "CERCA_ABITANTE_DATO_ID_MUNICIPIO";
	public static final String RIMUOVI_MUNICIPIO_E_ABITANTI = "RIMUOVI_MUNICIPIO_E_ABITANTI";
	public static final String ELENCA_TUTTI_I_MUNICIPI = "ELENCA_TUTTI_I_MUNICIPI";
	public static final String FIND_BY_EXAMPLE_BY_VIA = "FIND_BY_EXAMPLE_BY_VIA";
	public static final String UPDATE_ABITANTE_SET_ETA = "UPDATE_ABITANTE_SET_ETA";
	public static final String CARICA_MUNICIPIO_EAGER = "CARICA_MUNICIPIO_EAGER";
	public static final String REMOVE_CON_ECCEZIONE_VA_IN_ROLLBACK = "REMOVE_CON_ECCEZIONE_VA_IN_ROLLBACK";
	public static final String ABITANTI_MUNICIPIO_COMINCIA_CON = "ABITANTI_MUNICIPIO_COMINCIA_CON";
	public static final String ABITANTI_UBICAZIONE_CONTIENE = "ABITANTI_UBICAZIONE_CONTIENE";
	public static final String MUNICIPIO_COGNOME_ABITANTE_CONTIENE = "MUNICIPIO_COGNOME_ABITANTE_CONTIENE";
	public static final String CONTA_MUNICIPI_CON_MINORENNI = "CONTA_MUNICIPI_CON_MINORENNI";

	public void eseguiBatteriaDiTest(String casoDaTestare) {

		Faker faker = new Faker();

		try {
			switch (casoDaTestare) {
			case INSERISCI_NUOVO_MUNICIPIO:
				// creo nuovo municipio
				Municipio nuovoMunicipio = new Municipio("Municipio III", "III", "Via dei Nani");
				// salvo
				municipioService.inserisciNuovo(nuovoMunicipio);
				System.out.println("Municipio appena inserito: " + nuovoMunicipio);
				break;

			case INSERISCI_NUOVO_MUNICIPIO_FAKER_LOOP_20:
				for (int i = 0; i < 20; i++) {
					Municipio nuovoMunicipioTemp = new Municipio(faker.address().country(),
							faker.number().numberBetween(1, 99) + "", faker.address().streetAddress());
					municipioService.inserisciNuovo(nuovoMunicipioTemp);
					System.out.println("Municipio appena inserito: " + nuovoMunicipioTemp.toString());
				}
				break;

			case INSERISCI_ABITANTI_DATO_UN_MUNICIPIO:
				// / creo nuovo abitante
				Abitante nuovoAbitante = new Abitante("Pluto", "Plutorum", 77, "Via Lecce");
				nuovoAbitante.setMunicipio(municipioService.caricaSingoloMunicipio(22));
				// salvo
				abitanteService.inserisciNuovo(nuovoAbitante);
				break;

			case INSERISCI_ABITANTI_DATO_UN_MUNICIPIO_FAKER_LOOP_20:
				for (int i = 0; i < 20; i++) {
					Abitante nuovoAbitanteTemp = new Abitante(faker.name().firstName(), faker.name().lastName(),
							faker.number().numberBetween(1, 99), faker.address().fullAddress());
					Municipio municipioTemp = municipioService
							.caricaSingoloMunicipio(faker.number().numberBetween(1, 60));
					nuovoAbitanteTemp.setMunicipio(municipioTemp);
					abitanteService.inserisciNuovo(nuovoAbitanteTemp);
					System.out.println(nuovoAbitanteTemp.toString() + " appena inserito, in municipio: "
							+ municipioTemp.toString());
				}
				break;

			case CERCA_ABITANTE_DATO_ID_MUNICIPIO:
				// stampo gli abitanti di un determinato municipio

				for (Abitante abitante : abitanteService
						.cercaAbitanteInMunicipio(municipioService.caricaSingoloMunicipio(22))) {
					System.out.println(abitante.toString());
				}
				break;

			case RIMUOVI_MUNICIPIO_E_ABITANTI:
				// per cancellare tutto il municipio
				municipioService.rimuovi(municipioService.caricaSingoloMunicipio(24));
				break;

			case ELENCA_TUTTI_I_MUNICIPI:
				// elencare i municipi
				System.out.println("Elenco i municipi:");
				for (Municipio municipioItem : municipioService.listAllMunicipi()) {
					System.out.println(municipioItem);
				}
				break;

			case FIND_BY_EXAMPLE_BY_VIA:
				System.out.println("########### EXAMPLE ########################");
				// find by example: voglio ricercare i municipi con
				// ubicazione'Via dei Grandi'
				Municipio municipioExample = new Municipio();
				municipioExample.setUbicazione("Via dei Nani");
				for (Municipio municipioItem : municipioService.findByExample(municipioExample)) {
					System.out.println(municipioItem);
				}
				break;

			case UPDATE_ABITANTE_SET_ETA:
				// carico un abitante e cambio eta
				Abitante abitanteEsistente = abitanteService.caricaSingoloAbitante(14);
				if (abitanteEsistente != null) {
					abitanteEsistente.setEta(50);
					abitanteService.aggiorna(abitanteEsistente);
				}
				break;

			case CARICA_MUNICIPIO_EAGER:
				// quando carico un Municipio ho già i suoi abitanti
				Municipio municipioACaso = municipioService.caricaSingoloMunicipioEagerAbitanti(23);
				if (municipioACaso != null) {
					for (Abitante abitanteItem : municipioACaso.getAbitanti()) {
						System.out.println(abitanteItem);
					}
				}
				break;

			case REMOVE_CON_ECCEZIONE_VA_IN_ROLLBACK:
				// Test transaction rollback provando a cancellare l'ultimo
				// inserito
				List<Municipio> allMunicipi = municipioService.listAllMunicipi();
				System.out.println("...size before..." + allMunicipi.size());
				try {
					Municipio ultimoInserito = allMunicipi.get(allMunicipi.size() - 1);

					municipioService.removeConEccezione(ultimoInserito);
				} catch (Exception e) {
					e.printStackTrace();
				}

				allMunicipi = municipioService.listAllMunicipi();
				System.out.println("...size after..." + allMunicipi.size());
				break;

			case ABITANTI_MUNICIPIO_COMINCIA_CON:
				for (Abitante abitante : abitanteService.trovaAbitantiInMucipioCheCominciaCon("Uni")) {
					System.out.println(abitante.toString());
				}
				break;

			case ABITANTI_UBICAZIONE_CONTIENE:
				for (Abitante abitante : abitanteService.trovaAbitantiUbicazioneMunicipioContiene("ard")) {
					System.out.println(abitante.toString());
				}
				break;

			case MUNICIPIO_COGNOME_ABITANTE_CONTIENE:
				for (Municipio municipio : municipioService.trovaTuttiConCognomeAbitante("Yost")) {
					System.out.println(municipio.toString());
				}
				break;

			case CONTA_MUNICIPI_CON_MINORENNI:
				System.out.println(municipioService.countByAbitanteMinore());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
