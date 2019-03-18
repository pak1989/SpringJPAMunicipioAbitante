package it.municipioabitantejpa.dao.municipio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Component;

import it.municipioabitantejpa.model.Municipio;

@Component
public class MunicipioDAOImpl implements MunicipioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Municipio> list() {
		// dopo la from bisogna specificare il nome dell'oggetto (lettera
		// maiuscola) e
		// non la tabella
		return entityManager.createQuery("from Municipio").getResultList();
	}

	@Override
	public Municipio get(long id) {
		Municipio result = (Municipio) entityManager.find(Municipio.class, id);
		return result;
	}

	@Override
	public Municipio findEagerFetch(long idMunicipio) {
		Query q = entityManager.createQuery("SELECT m FROM Municipio m JOIN FETCH m.abitanti a WHERE m.id = :id");
		q.setParameter("id", idMunicipio);
		return (Municipio) q.getSingleResult();
	}

	@Override
	public void update(Municipio municipioInstance) {
		municipioInstance = entityManager.merge(municipioInstance);
	}

	@Override
	public void insert(Municipio municipioInstance) {
		entityManager.persist(municipioInstance);
	}

	@Override
	public void delete(Municipio municipioInstance) {
		entityManager.remove(entityManager.merge(municipioInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Municipio> findByExample(Municipio municipioInstance) {
		Session session = (Session) entityManager.getDelegate();
		Example municipioExample = Example.create(municipioInstance).excludeZeroes();
		Criteria criteria = session.createCriteria(Municipio.class).add(municipioExample);
		return criteria.list();
	}

	@Override
	public void refresh(Municipio municipioInstance) {
		entityManager.refresh(entityManager.merge(municipioInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Municipio> findAllByCognomeAbitante(String cognomeInput) {
		Query query = entityManager
				.createQuery("SELECT mun FROM Municipio mun JOIN mun.abitanti ab WHERE ab.cognome = :cognomeInput")
				.setParameter("cognomeInput", cognomeInput);
		return query.getResultList();
	}

	@Override
	public int countByAbitanteMinore() {
		Query query = entityManager
				.createQuery("SELECT mun.id FROM Municipio mun JOIN mun.abitanti ab WHERE ab.eta < 18 GROUP BY mun.id");
		return query.getResultList().size();
		// List<Object[]> results
		// for (Object[] elem : results) {
		// return ((Number) elem[1]).intValue();
		// }
		// Number count = (Number) query.
		// return (int) count.longValue(); SELECT mun, COUNT (mun) FROM Abitante ab JOIN
		// ab.municipio mun WHERE ab.eta < 18 GROUP BY mun
	}

}
