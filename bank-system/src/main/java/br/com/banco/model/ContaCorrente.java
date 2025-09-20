package br.com.banco.model;

import br.com.banco.exceptions.SaldoInsuficienteException;

public class ContaCorrente extends Conta 
{
	private static final Double TAXA = 0.01;
	
	private Double limiteChequeEspecial;

	public ContaCorrente(Integer numero, Cliente titular, Double limiteChequeEspecial) {
		super(numero, titular);
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

	@Override
	public void sacar(Double valor) 
	{
		Double valorComTaxa = valor + this.calcularTarifa(valor);
		
		if(valorComTaxa <= this.getSaldo() + limiteChequeEspecial)
			debitar(valorComTaxa);
		else
			throw new SaldoInsuficienteException("Saldo Insuficiente");
	}

	@Override
	public void transferir(Double valor, Conta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
		
	}

	@Override
	public Double calcularTarifa(Double valor) {
		return valor * TAXA;
	}
	
}
