package it.municipioabitantejpa.dao.municipio;

import java.util.List;

import it.municipioabitantejpa.dao.IBaseDAO;
import it.municipioabitantejpa.model.Municipio;

public interface MunicipioDAO extends IBaseDAO<Municipio> {
	public Municipio findEagerFetch(long idMunicipio);

	public List<Municipio> findAllByCognomeAbitante(String cognomeInput);

	public int countByAbitanteMinore();
}
