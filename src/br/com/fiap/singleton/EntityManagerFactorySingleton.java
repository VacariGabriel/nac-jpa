package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {

	//atributo estatico que possui o objeto unico
	private static EntityManagerFactory fabrica;
	
	//construtor privado
	private EntityManagerFactorySingleton() {}
	
	//metodo estatico que retorna a unica instancia
	public static EntityManagerFactory getInstance() {
		if (fabrica == null) {
			fabrica = Persistence.createEntityManagerFactory("oracle");
		}
		return fabrica;
	}
	
}



