package br.com.fiap.dao;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.entity.Corrida;

public interface CorridaDAO extends GenericDAO<Corrida, Integer> {
	List<Corrida> buscaPorIntervaloTempo(Calendar inicio, Calendar fim);
}
