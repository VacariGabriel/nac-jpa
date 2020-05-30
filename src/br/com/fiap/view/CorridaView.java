package br.com.fiap.view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.CorridaDAO;
import br.com.fiap.dao.impl.CorridaDAOImpl;
import br.com.fiap.entity.Corrida;
import br.com.fiap.entity.Genero;
import br.com.fiap.entity.Motorista;
import br.com.fiap.entity.Passageiro;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class CorridaView {
	public static void main(String[] args) {
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();
		
		CorridaDAO corridaDAO = new CorridaDAOImpl(em);
		
		Passageiro passageiro = new Passageiro("Passageiro", new GregorianCalendar(1997, 06, 06), Genero.FEMININO);
		
		Motorista motorista = new Motorista(20, "Lucas da Silva Junior",
				new GregorianCalendar(2020, 4, 20), null, null);
		
		Corrida corrida = new Corrida("Mauá", "Santo André", new GregorianCalendar(2020, 4, 25), 25f, motorista, passageiro, null);		
		Corrida corrida2 = new Corrida("São Caetano", "Mooca", new GregorianCalendar(2020, 4, 24), 35f, motorista, passageiro, null);
		Corrida corrida3 = new Corrida("Av. Paulista", "Consolacao", new GregorianCalendar(2020, 4, 20),
				12f, motorista, passageiro, null);
		
		try {
			corridaDAO.cadastrar(corrida);
			corridaDAO.cadastrar(corrida2);
			corridaDAO.cadastrar(corrida3);
			corridaDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//2. Buscar por todas as corridas por um intervalo de datas:
		Calendar inicio = new GregorianCalendar(2020, 4, 24);
		Calendar fim = new GregorianCalendar(2020, 4, 25);
		
		List<Corrida> corridas = corridaDAO.buscaPorIntervaloTempo(inicio, fim);
		
		System.out.println("2-Buscar por todas as corridas por um intervalo de datas: ");
		corridas.forEach(corridinhaMarota -> System.out.println("Origem: "+corridinhaMarota.getOrigem()));
		
		//4-Buscar por todas as corridas de um motorista
		List<Corrida> corridasDoMotorista = corridaDAO.buscarPorTodasCorridasDoMotorista(motorista);
		
		System.out.println("\n4-Buscar por todas as corridas de um motorista: ");
		corridasDoMotorista.forEach(corridaDoMotorista -> System.out.println("Corrida " + corridaDoMotorista.getCodigo() + ", Destino: " + corrida.getDestino()));
	
		//5-Contar a quantidade de corridas de um passageiro: 
		int codigoPassageiro = 1;
		long qtdeDeCorridaPorPassageiro = corridaDAO.qtdeCorridaPorPasseiro(codigoPassageiro);
		System.out.println("\n5-Contar a quantidade de corridas de um passageiro: ");
		System.out.println("Código do passageiro: "+ codigoPassageiro + ", quantidade: " + qtdeDeCorridaPorPassageiro);
		em.close();
		factory.close();
	}
}
