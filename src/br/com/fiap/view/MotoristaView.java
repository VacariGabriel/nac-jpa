package br.com.fiap.view;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.MotoristaDAO;
import br.com.fiap.dao.impl.MotoristaDAOImpl;
import br.com.fiap.entity.Motorista;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class MotoristaView {
	public static void main(String[] args) {
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();
		
		MotoristaDAO motoristaDAO = new MotoristaDAOImpl(em);
		
		Motorista motorista = new Motorista(20, "Lucas da Silva Junior",
				new GregorianCalendar(2020, 4, 20), null, null);
				
		motoristaDAO.cadastrar(motorista);
		
		List<Motorista> motoristas = motoristaDAO.buscaMotoristaPorParteNome("Luc");
		
		motoristas.forEach(moto -> System.out.println(moto.getNome()));
	}
}
