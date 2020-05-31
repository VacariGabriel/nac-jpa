package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.PagamentoDAO;
import br.com.fiap.entity.Pagamento;

public class PagamentoDAOImpl extends GenericDAOImpl<Pagamento, Integer> implements PagamentoDAO{

	public PagamentoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public float somarPagamentosDeUmPassageiro(int codigoPassageiro) {
		return em.createQuery("Select SUM(pagamento.valor) from Corrida c where passageiro.codigo = :codigo", Float.class)
				.setParameter("codigo", codigoPassageiro)
				.getSingleResult();
	}

}
