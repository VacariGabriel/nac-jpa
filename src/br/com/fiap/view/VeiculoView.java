package br.com.fiap.view;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.VeiculoDAO;
import br.com.fiap.dao.impl.VeiculoDAOImpl;
import br.com.fiap.entity.Veiculo;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class VeiculoView {

	public static void main(String[] args) {
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();
				
		VeiculoDAO veiculoDAO = new VeiculoDAOImpl(em);

		//3. Buscar por todos os veículos por valor mínimo do ano:
		try {
			Veiculo veiculo = new Veiculo("ABC-1010", "Azul", 2010);
			Veiculo veiculo2 = new Veiculo("ABC-1020", "Azul", 2013);
			veiculoDAO.cadastrar(veiculo);
			veiculoDAO.cadastrar(veiculo2);
			veiculoDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Veiculo> veiculos = veiculoDAO.buscarTodosOsVeiculosPorAnoMinimo(2010);

		veiculos.forEach(veiculozinho -> System.out.println(veiculozinho.getPlaca()));
		
		em.close();
		factory.close();
	}

}
