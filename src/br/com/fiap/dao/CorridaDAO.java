package br.com.fiap.dao;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.entity.Corrida;
import br.com.fiap.entity.Motorista;

public interface CorridaDAO extends GenericDAO<Corrida, Integer> {
	List<Corrida> buscaPorIntervaloTempo(Calendar inicio, Calendar fim);
	
	List<Corrida> buscarPorTodasCorridasDoMotorista(Motorista motorista);

	long qtdeCorridaPorPasseiro(int codigo);

	List<Corrida> buscarPorParteDoNomeDoPassageiro(String nome);

	long ContarCorridasDeUmMotoristaPorData(int codigoMotorista, Calendar inicio, Calendar fim);
	
	List<Corrida>MaioresValoresDeCorridaPorPassageiro(int codigoPassageiro);
}
