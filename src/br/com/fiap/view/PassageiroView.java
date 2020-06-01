package br.com.fiap.view;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.CorridaDAO;
import br.com.fiap.dao.PagamentoDAO;
import br.com.fiap.dao.impl.CorridaDAOImpl;
import br.com.fiap.dao.impl.PagamentoDAOImpl;
import br.com.fiap.entity.Corrida;
import br.com.fiap.entity.FormaPagamento;
import br.com.fiap.entity.Genero;
import br.com.fiap.entity.Motorista;
import br.com.fiap.entity.Pagamento;
import br.com.fiap.entity.Passageiro;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class PassageiroView {

	public static void main(String[] args) {
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();
		
		CorridaDAO corridaDAO = new CorridaDAOImpl(em);
		PagamentoDAO pagamentoDAO = new PagamentoDAOImpl(em);
		
		
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
		int codigoPassageiro = 1;
		
		//7. Somar todos os valores de pagamentos realizados por um passageiro:
		System.out.println("7. Somar todos os valores de pagamentos realizados por um passageiro");
		double total = pagamentoDAO.somarTodosPagamentosDoPassageiro(codigoPassageiro);
		System.out.println("Codigo do passageiro: "+ codigoPassageiro + ", total: "+total);
		
		//9. Buscar pelos 10 maiores valores de corrida realizados por um passageiro	
		System.out.println("\n9. Buscar pelos 10 maiores valores de corrida realizados por um passageiro:");
		List<Corrida> maioresValores = corridaDAO.MaioresValoresDeCorridaPorPassageiro(codigoPassageiro);
		maioresValores.forEach(item -> System.out.println("Corrida valor: " + item.getValor()));
		
		em.close();
		factory.close();
	}
	

}
