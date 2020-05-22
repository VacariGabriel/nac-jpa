package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.MotoristaDAO;
import br.com.fiap.entity.Motorista;

public class MotoristaDAOImpl extends GenericDAOImpl<Motorista, Long> implements MotoristaDAO {

	public MotoristaDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Motorista> buscaMotoristaPorParteNome(String parteNome) {
		TypedQuery<Motorista> query =  em.createQuery(
				"from Motorista m where m.nome like :pn", Motorista.class);
		query.setParameter("pn", "%" + parteNome + "%");
		query.setMaxResults(50);		
		return query.getResultList();
	}

} 