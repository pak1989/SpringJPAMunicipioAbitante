package it.municipioabitantejpa.service.abitante;

import it.municipioabitantejpa.model.Abitante;
import it.municipioabitantejpa.model.Municipio;

import java.util.List;

public interface AbitanteService {

	public List<Abitante> listAllAbitanti();

	public Abitante caricaSingoloAbitante(long id);

	public void aggiorna(Abitante abitanteInstance);

	public void inserisciNuovo(Abitante abitanteInstance);

	public void rimuovi(Abitante abitanteInstance);

	public List<Abitante> findByExample(Abitante example);
	
	public void refresh(Abitante abitanteInstance);
	
	public List<Abitante> cercaAbitanteInMunicipio(Municipio input);
	
	public List<Abitante> trovaAbitantiInMucipioCheCominciaCon(String stringInput);

	public List<Abitante> trovaAbitantiUbicazioneMunicipioContiene(String stringInput);
}
