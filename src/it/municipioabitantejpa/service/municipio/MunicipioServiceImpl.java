package it.municipioabitantejpa.service.municipio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.municipioabitantejpa.dao.municipio.MunicipioDAO;
import it.municipioabitantejpa.model.Municipio;

@Component
public class MunicipioServiceImpl implements MunicipioService {

	@Autowired
	private MunicipioDAO municipioDAO;

	@Transactional(readOnly = true)
	public List<Municipio> listAllMunicipi() {
		return municipioDAO.list();
	}

	@Transactional(readOnly = true)
	public Municipio caricaSingoloMunicipio(long id) {
		return municipioDAO.get(id);
	}

	@Transactional(readOnly = true)
	public Municipio caricaSingoloMunicipioEagerAbitanti(long idMunicipio) {
		return municipioDAO.findEagerFetch(idMunicipio);
	}

	@Transactional
	public void aggiorna(Municipio municipioInstance) {
		municipioDAO.update(municipioInstance);
	}

	@Transactional
	public void inserisciNuovo(Municipio municipioInstance) {
		municipioDAO.insert(municipioInstance);
	}

	@Transactional
	public void rimuovi(Municipio municipioInstance) {
		municipioDAO.delete(municipioInstance);
	}

	@Transactional(readOnly = true)
	public List<Municipio> findByExample(Municipio example) {
		return municipioDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public void refresh(Municipio municipioInstance) {
		municipioDAO.refresh(municipioInstance);
	}

	@Transactional
	public void removeConEccezione(Municipio municipioInstance) {
		municipioDAO.delete(municipioInstance);
		throw new RuntimeException("Eccezione di prova transazione");
	}

	@Override
	public List<Municipio> trovaTuttiConCognomeAbitante(String cognomeInput) {
		return municipioDAO.findAllByCognomeAbitante(cognomeInput);
	}

	@Override
	public int countByAbitanteMinore() {
		return municipioDAO.countByAbitanteMinore();
	}
}
