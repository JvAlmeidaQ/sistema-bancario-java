package br.com.banco.service;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import br.com.banco.exceptions.AutenticacaoException;
import br.com.banco.exceptions.ContaNaoEncontradaException;
import br.com.banco.model.Cliente;
import br.com.banco.model.Conta;
import br.com.banco.model.ContaCorrente;
import br.com.banco.model.ContaInvestimento;
import br.com.banco.model.ContaPoupanca;
import br.com.banco.model.TipoConta;
import  br.com.banco.util.IdGenerator;

public class BancoService {
	
	private Map<Integer, Conta> contas = new HashMap<>();
	private Map<String, Cliente> clientes  = new HashMap<>();
	private int proxNumConta = 100;
	
	public void criarCliente(String nome, String cpf, String senha)
	{
		if(clientes.containsKey(cpf))
			throw new AutenticacaoException("CPF já cadastrado");
		
		clientes.put(cpf, new Cliente(IdGenerator.geraId(),nome ,cpf ,senha ));
	}
	
	public void criarConta(Cliente cliente, TipoConta tipoConta)
	{
		Conta novaConta;
	    switch (tipoConta) {
	        case CONTA_CORRENTE:
	            novaConta = new ContaCorrente(proxNumConta,cliente);
	            break;
	        case CONTA_POUPANCA:
	            novaConta = new ContaPoupanca(proxNumConta,cliente);
	            break;
	        case CONTA_INVESTIMENTO:
	            novaConta = new ContaInvestimento(proxNumConta,cliente);
	            break;
	        default:
	            throw new ContaNaoEncontradaException("Tipo de conta inválido: " + tipoConta);
	    }
	    
	    contas.put(proxNumConta, novaConta);
	    proxNumConta++;
	    
	}
	
	public void depositar(Integer numConta, Double valor)
	{
		 if(!contas.containsKey(numConta))
		 {
	 		throw new AutenticacaoException("Não existe conta com esse numero!");
		 }
		 
		 contas.get(numConta).depositar(valor);
	}
	
	public void sacar(Integer numConta, Double valor, String senha)
	{
		
		if(!contas.containsKey(numConta))
				throw new AutenticacaoException("Não existe conta com esse numero!");
		 
	 	if(!contas.get(numConta).getTitular().autenticar(senha))
		 	 	throw new AutenticacaoException("As senhas não são iguais");
		
		 contas.get(numConta).sacar(valor);
		 
	}
	
	public void transferir(Conta origem, Conta Destino, Double Valor, String senha)
	{
		if(!contas.containsKey(origem.getNumero()))
				throw new AutenticacaoException("A conta de Origem não existe");
		
		if(!contas.containsKey(Destino.getNumero()))
				throw new AutenticacaoException("A conta Destino não existe");
		
		if(!contas.get(origem.getNumero()).getTitular().autenticar(senha))
	 	 	throw new AutenticacaoException("As senhas não são iguais");
		
		contas.get(origem.getNumero()).transferir(Valor, Destino);
		
	}
	
	public void extrato(Integer numConta, Date inicio, Date fim )
	{
	 
	}
}
