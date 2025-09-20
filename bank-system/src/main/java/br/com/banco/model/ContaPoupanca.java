package br.com.banco.model;

import java.util.List;

import br.com.banco.exceptions.SaldoInsuficienteException;

public class ContaPoupanca extends Conta {
	
	private Double taxaJurosMensal;
	
	public ContaPoupanca(Integer numero, Cliente titular, Double taxaJurosMensal ) 
	{
		super(numero, titular);
		this.taxaJurosMensal = taxaJurosMensal;
	}

	@Override
	public void sacar(Double valor)
	{
		if(valor <= this.getSaldo())
			debitar(valor);
		else
			throw new SaldoInsuficienteException("Saldo Insuficiente");
	}

	@Override
	public void transferir(Double valor, Conta contaDestino) 
	{
		if (!contaDestino.getTitular().equals(this.getTitular()))
		{
	        throw new IllegalArgumentException("Transferências da poupança só são permitidas para contas do mesmo titular.");
	    }
		
		if (!(contaDestino instanceof ContaCorrente)) {
	        throw new IllegalArgumentException("Transferências da poupança só são permitidas para uma Conta Corrente.");
	    }
		
		this.sacar(valor);
		contaDestino.debitar(valor);
		
	}
	
	public void aplicarJuros()
	{
		Double valorComJuros = this.getSaldo() * this.taxaJurosMensal;
		this.creditar(valorComJuros);
	}

	@Override
	public Double calcularTarifa(Double valor) 
	{
		return 0.00;
	}
	
	
}
