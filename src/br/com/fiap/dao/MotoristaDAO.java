package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Motorista;

public interface MotoristaDAO extends GenericDAO<Motorista, Long>{
	List<Motorista> buscaMotoristaPorParteNome(String parteNome);

}



