package br.com.fiap.view;

import java.util.ArrayList;
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
		
		Passageiro passageiro = new Passageiro("Passageiro", new GregorianCalendar(1997, 06, 06), Genero.FEMININO);
		
		Motorista motorista = new Motorista(20, "Lucas da Silva Junior",
				new GregorianCalendar(2020, 4, 20), null, null);
		
		Corrida corrida = new Corrida("Mauá", "Santo André", new GregorianCalendar(2020, 4, 25), 25f, motorista, passageiro, null);		
		Corrida corrida2 = new Corrida("São Caetano", "Mooca", new GregorianCalendar(2020, 4, 24), 35f, motorista, passageiro, null);
		Corrida corrida3 = new Corrida("Av. Paulista", "Consolacao", new GregorianCalendar(2020, 4, 20),
				12f, motorista, passageiro, null);
		
		Pagamento pagamento = new Pagamento(new GregorianCalendar(2020, 01, 04), 200f, FormaPagamento.DINHEIRO);
		Pagamento pagamento2 = new Pagamento(new GregorianCalendar(2020, 01, 10), 100f, FormaPagamento.DEBITO);
		Pagamento pagamento3 = new Pagamento(new GregorianCalendar(2019, 03, 23), 50f, FormaPagamento.CREDITO);

		pagamento.setCorrida(corrida);
		pagamento2.setCorrida(corrida2);
		pagamento3.setCorrida(corrida3);
		
		corrida.setPagamento(pagamento);
		corrida2.setPagamento(pagamento2);
		corrida3.setPagamento(pagamento3);
		
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
		
		//10. Buscar pelas corridas realizadas entre um passageiro e motorista específicos:
		long codMotorista = 20;
		
		System.out.println("\n10. Buscar pelas corridas realizadas entre um passageiro e motorista específicos");
		List<Corrida> corridaPorPassEMot = corridaDAO.BuscarCorridaPorPassageiroEMotorista(codigoPassageiro, codMotorista);
		System.out.println("Cod passageiro: " + codigoPassageiro + ", cod motorista: " + codMotorista);
		corridaPorPassEMot.forEach(item -> System.out.println("Corrida origem: " + item.getOrigem()));
		
		em.close();
		factory.close();	}
}
