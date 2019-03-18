package it.municipioabitantejpa.service.abitante;

import it.municipioabitantejpa.dao.abitante.AbitanteDAO;
import it.municipioabitantejpa.model.Abitante;
import it.municipioabitantejpa.model.Municipio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AbitanteServiceImpl implements AbitanteService {

	@Autowired
	private AbitanteDAO abitanteDAO;

	@Transactional(readOnly = true)
	public List<Abitante> listAllAbitanti() {
		return abitanteDAO.list();
	}

	@Transactional(readOnly = true)
	public Abitante caricaSingoloAbitante(long id) {
		return abitanteDAO.get(id);
	}

	@Transactional
	public void aggiorna(Abitante abitanteInstance) {
		abitanteDAO.update(abitanteInstance);
	}

	@Transactional
	public void inserisciNuovo(Abitante abitanteInstance) {
		abitanteDAO.insert(abitanteInstance);
	}

	@Transactional
	public void rimuovi(Abitante abitanteInstance) {
		abitanteDAO.delete(abitanteInstance);
	}

	@Transactional(readOnly = true)
	public List<Abitante> findByExample(Abitante example) {
		return abitanteDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public void refresh(Abitante abitanteInstance) {
		abitanteDAO.refresh(abitanteInstance);
	}

	@Override
	public List<Abitante> cercaAbitanteInMunicipio(Municipio input) {
		return abitanteDAO.findAllByMunicipio(input);
	}

	@Override
	public List<Abitante> trovaAbitantiInMucipioCheCominciaCon(String stringInput) {
		return abitanteDAO.findAllAbitantiByMunicipioStratsWhith(stringInput);
	}

	@Override
	public List<Abitante> trovaAbitantiUbicazioneMunicipioContiene(String stringInput) {
		return abitanteDAO.findAllAbitantiByMunicipioUbicazioneContains(stringInput);
	}

}
