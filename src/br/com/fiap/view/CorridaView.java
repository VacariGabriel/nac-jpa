package br.com.fiap.view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.CorridaDAO;
import br.com.fiap.dao.impl.CorridaDAOImpl;
import br.com.fiap.entity.Corrida;
import br.com.fiap.entity.FormaPagamento;
import br.com.fiap.entity.Genero;
import br.com.fiap.entity.Motorista;
import br.com.fiap.entity.Pagamento;
import br.com.fiap.entity.Passageiro;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class CorridaView {
	public static void main(String[] args) {
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();
		
		CorridaDAO corridaDAO = new CorridaDAOImpl(em);
		
		Pagamento pagamento = new Pagamento(new GregorianCalendar(2020, 01, 04), 200f, FormaPagamento.DINHEIRO);
		
		Passageiro passageiro = new Passageiro("Passageiro", new GregorianCalendar(1997, 06, 06), Genero.FEMININO);
		
		Motorista motorista = new Motorista(20, "Lucas da Silva Junior",
				new GregorianCalendar(2020, 4, 20), null, null);
		
		Corrida corrida = new Corrida("Mauá", "Santo André", new GregorianCalendar(2020, 4, 25), 25f, motorista, passageiro, pagamento);		
		Corrida corrida2 = new Corrida("São Caetano", "Mooca", new GregorianCalendar(2020, 4, 24), 35f, motorista, passageiro, pagamento);
		Corrida corrida3 = new Corrida("Av. Paulista", "Consolacao", new GregorianCalendar(2020, 4, 20),
				12f, motorista, passageiro, pagamento);
		
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
		corridas.forEach(item -> System.out.println("Origem: "+item.getOrigem()));
		
		//4-Buscar por todas as corridas de um motorista
		List<Corrida> corridasDoMotorista = corridaDAO.buscarPorTodasCorridasDoMotorista(motorista);
		
		System.out.println("\n4-Buscar por todas as corridas de um motorista: ");
		corridasDoMotorista.forEach(item -> System.out.println("Corrida " + item.getCodigo() + ", Destino: " + item.getDestino()));
	
		//5-Contar a quantidade de corridas de um passageiro: 
		int codigoPassageiro = 1;
		long qtdeDeCorridaPorPassageiro = corridaDAO.qtdeCorridaPorPasseiro(codigoPassageiro);
		System.out.println("\n5-Contar a quantidade de corridas de um passageiro: ");
		System.out.println("Código do passageiro: "+ codigoPassageiro + ", quantidade de corridas: " + qtdeDeCorridaPorPassageiro);
		
		//6-Buscar por todas as corridas por parte do nome do passageiro
		System.out.println("\n6-Buscar por todas as corridas por parte do nome do passageiro");
		String nomeDoPassageiro = "Pass";
		List<Corrida> buscarPorNomePassageiro = corridaDAO.buscarPorParteDoNomeDoPassageiro(nomeDoPassageiro);
		buscarPorNomePassageiro.forEach(item -> System.out.println("Nome: " + nomeDoPassageiro + ", código da corrida: " + item.getCodigo()));
		em.close();
		factory.close();
	}
}
