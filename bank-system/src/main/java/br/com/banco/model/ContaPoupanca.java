package br.com.banco.model;

import br.com.banco.exceptions.SaldoInsuficienteException;

public class ContaPoupanca extends Conta {
	
	private static final Double TAXA_JUROS_MENSAL = 0.0125;
	
	public ContaPoupanca(Integer numero, Cliente titular ) 
	{
		super(numero, titular);
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
		Double valorComJuros = this.getSaldo() * TAXA_JUROS_MENSAL;
		this.creditar(valorComJuros);
	}

	@Override
	public Double calcularTarifa(Double valor) 
	{
		return 0.00;
	}
	
	
}
