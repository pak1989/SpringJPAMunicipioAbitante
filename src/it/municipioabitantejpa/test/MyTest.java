package it.municipioabitantejpa.test;

import it.municipioabitantejpa.service.BatteriaDiTestService;
import it.municipioabitantejpa.service.MyServiceFactory;

import org.springframework.stereotype.Component;

@Component
public class MyTest {

	public static void main(String[] args) {

		// DA VALORIZZARE SECONDO I CASI ESPOSTI NELLE COSTANTI SOPRA
		// ##########################################################
		String casoDaTestare = BatteriaDiTestService.CONTA_MUNICIPI_CON_MINORENNI;
		// ##########################################################

		System.out.println("################ START   #################");
		System.out.println("################ eseguo il test " + casoDaTestare + "  #################");

		MyServiceFactory.getBatteriaDiTestServiceInstance().eseguiBatteriaDiTest(casoDaTestare);

		System.out.println("################ FINE   #################");

	}
}

// abitanteDAO
// tutti gli abitanti in cui i municipi la cui descrizione inizia con
// findAllAbitantiByMunicipioStratsWhith
// tutti gli abitanti che risiedono nei municipi la cui ubicazione contiene il
// pezzo di stringa in input
// findAllAbitantiByMunicipioDescrizioneContains
// municicpioDAO
// tutti i municipi che hanno al loro interno persone di cognome String
// findAllByCognomeAbitante
// conta i municipi che contengono abitanti minorenni
// countByAbitanteMinore

// CASA
// CLASSE Utente CON nome cognome username password e date_created
// CLASSE Ruolo CON id descrizione("administrator") codice("adm")
// CLASSE Audit id descrizione_operazione("agg tabbella persona")
// codice_esito_operazion("ok") messaggio_errore_eventuale utente_id

// METODI
// 1. dato un codice esito operazione(del tipo ok or not ok), voglio tutti i
// ruoli degli utenti associati a quella operazione (es: mi dai tutti gli utenti
// che hanno fallito operazione)

// 2. dato un ruolo in ingresso voglio la lista (di Stringhe) di tutte le
// password di quegli utenti associati a quel ruolo

// 3. dato un ruolo in ingresso voglio la lista di tutte le voci di audit associate a quel ruolo. 