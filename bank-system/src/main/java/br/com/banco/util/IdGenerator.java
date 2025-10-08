package br.com.banco.util;

public class IdGenerator {
	
	private static Integer id = 100;
	
	private IdGenerator() {}
	
	public static void inicializar(Integer valorUltimoId) {
		id = valorUltimoId;
	}
	
	public static Integer geraId()
	{
		return id++;
	}
}
