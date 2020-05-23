package br.com.fiap.view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.CorridaDAO;
import br.com.fiap.dao.impl.CorridaDAOImpl;
import br.com.fiap.entity.Corrida;
import br.com.fiap.entity.Motorista;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class CorridaView {
	public static void main(String[] args) {
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();
		
		CorridaDAO corridaDAO = new CorridaDAOImpl(em);
		
		Corrida corrida = new Corrida("Mauá", "Santo André", new GregorianCalendar(2020, 4, 25), 25f);		
		Corrida corrida2 = new Corrida("São Caetano", "Mooca", new GregorianCalendar(2020, 5, 2), 35f);
		
		try {
			corridaDAO.cadastrar(corrida);
			corridaDAO.cadastrar(corrida2);
			corridaDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Calendar inicio = new GregorianCalendar(2020, 4, 24);
		Calendar fim = new GregorianCalendar(2020, 4, 25);
		
		List<Corrida> corridas = corridaDAO.buscaPorIntervaloTempo(inicio, fim);
		
		corridas.forEach(corridinhaMarota -> System.out.println(corridinhaMarota.getData()));
		
		//Buscar por todas as corridas de um motorista
		Motorista motorista = new Motorista(20, "Lucas da Silva Junior",
				new GregorianCalendar(2020, 4, 20), null, null);
		
		Corrida corrida3 = new Corrida("Av. Paulista", "Consolacao", new GregorianCalendar(2020, 4, 20),
				12f, motorista, null, null);
		
		try {
			corridaDAO.cadastrar(corrida3);
			corridaDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Corrida> corridasDoMotorista = corridaDAO.buscarPorTodasCorridasDoMotorista(motorista);
		
		corridasDoMotorista.forEach(corridaDoMotorista -> System.out.println(corrida.getOrigem()));
		
		em.close();
		factory.close();
	}
}
