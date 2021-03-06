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
		return em.createQuery("from Corrida c where c.data between :i and :f", Corrida.class)
				.setParameter("i", inicio)
				.setParameter("f", fim)
				.setMaxResults(30)
				.getResultList();
	}
	
	@Override
	public List<Corrida> buscarPorTodasCorridasDoMotorista(Motorista motorista) {
		TypedQuery<Corrida> query = em.createQuery("from Corrida c where c.motorista = :m", Corrida.class);
		query.setParameter("m", motorista);
		return query.getResultList();
	}

	@Override
	public long qtdeCorridaPorPasseiro(int codigo) {
		return em.createQuery("Select COUNT(c) from Corrida c where passageiro.codigo = :codigo", Long.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}

	@Override
	public List<Corrida> buscarPorParteDoNomeDoPassageiro(String nome) {
		return em.createQuery("from Corrida c where passageiro.nome like :nome", Corrida.class)
				.setParameter("nome", "%"+nome+"%")
				.setMaxResults(40)
				.getResultList();
	}

	@Override
	public long ContarCorridasDeUmMotoristaPorData(int codigoMotorista, Calendar inicio, Calendar fim) {
		return em.createQuery("Select Count(motorista) from Corrida c where c.data between :i and :f", Long.class)
		.setParameter("i", inicio)
		.setParameter("f", fim)
		.getSingleResult();
	}

	@Override
	public List<Corrida> MaioresValoresDeCorridaPorPassageiro(int codigoPassageiro) {
		return em.createQuery("from Corrida c where passageiro.codigo = :codigo ORDER BY valor desc", Corrida.class)
				.setParameter("codigo", codigoPassageiro)
				.setMaxResults(10)
				.getResultList();
	}

	@Override
	public List<Corrida> BuscarCorridaPorPassageiroEMotorista(int codigoPassageiro, long codMotorista) {
		return em.createQuery("from Corrida c where passageiro.codigo = :codP and motorista.numeroCarteira = :codM", Corrida.class)
				.setParameter("codP", codigoPassageiro)
				.setParameter("codM", codMotorista)
				.getResultList();
	}
}