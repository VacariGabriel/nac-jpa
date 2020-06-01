package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.PagamentoDAO;
import br.com.fiap.entity.Pagamento;

public class PagamentoDAOImpl extends GenericDAOImpl<Pagamento, Integer> implements PagamentoDAO{

	public PagamentoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public double somarTodosPagamentosDoPassageiro(int codPassageiro) {
		return em.createQuery("Select SUM(p.valor) from Pagamento p where corrida.passageiro.codigo = :codigo", Double.class)
				.setParameter("codigo", codPassageiro)
				.getSingleResult();
	}

}
