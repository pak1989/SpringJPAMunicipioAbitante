package it.municipioabitantejpa.dao.abitante;

import it.municipioabitantejpa.model.Abitante;
import it.municipioabitantejpa.model.Municipio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Component;

@Component
public class AbitanteDAOImpl implements AbitanteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Abitante> list() {
		return entityManager.createQuery("from Abitante").getResultList();
	}

	@Override
	public Abitante get(long id) {
		Abitante result = (Abitante) entityManager.find(Abitante.class, id);
		return result;
	}

	@Override
	public void update(Abitante abitanteInstance) {
		abitanteInstance = entityManager.merge(abitanteInstance);
	}

	@Override
	public void insert(Abitante abitanteInstance) {
		entityManager.persist(abitanteInstance);
	}

	@Override
	public void delete(Abitante abitanteInstance) {
		entityManager.remove(entityManager.merge(abitanteInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Abitante> findByExample(Abitante abitanteInstance) {
		Session session = (Session) entityManager.getDelegate();
		Example abitanteExample = Example.create(abitanteInstance).excludeZeroes();
		Criteria criteria = session.createCriteria(Abitante.class).add(abitanteExample);
		return criteria.list();
	}

	@Override
	public void refresh(Abitante abitanteInstance) {
		entityManager.refresh(entityManager.merge(abitanteInstance));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Abitante> findAllByMunicipio(Municipio input) {
		Query query = entityManager
				.createQuery("select u FROM Abitante u JOIN FETCH u.municipio mun WHERE mun.id = :idMunicipio")
				.setParameter("idMunicipio", input.getId());
		return (List<Abitante>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Abitante> findAllAbitantiByMunicipioStratsWhith(String stringInput) {
		Query query = entityManager
				.createQuery("select ab FROM Abitante ab JOIN FETCH ab.municipio mun WHERE mun.descrizione LIKE :stringInput")
				.setParameter("stringInput", stringInput + "%");
		return (List<Abitante>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Abitante> findAllAbitantiByMunicipioUbicazioneContains(String stringInput) {
		Query query = entityManager
				.createQuery("SELECT ab FROM Abitante ab JOIN FETCH ab.municipio mun WHERE mun.ubicazione LIKE CONCAT('%',:stringInput,'%')")
				.setParameter("stringInput", stringInput);
		return (List<Abitante>) query.getResultList();
	}

}
