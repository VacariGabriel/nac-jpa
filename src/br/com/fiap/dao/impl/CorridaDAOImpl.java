package br.com.fiap.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.CorridaDAO;
import br.com.fiap.entity.Corrida;
import br.com.fiap.entity.Motorista;

public class CorridaDAOImpl extends GenericDAOImpl<Corrida, Integer> implements CorridaDAO{

	public CorridaDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Corrida> buscaPorIntervaloTempo(Calendar inicio, Calendar fim) {
		return em.createQuery("from Corrida where between :i and :f", Corrida.class)
				.setParameter("i", inicio)
				.setParameter("f", fim)
				.getResultList();
	}

}
