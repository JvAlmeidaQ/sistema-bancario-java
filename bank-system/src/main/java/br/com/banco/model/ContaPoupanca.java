package br.com.banco.model;

import br.com.banco.exceptions.SaldoInsuficienteException;

public class ContaPoupanca extends Conta {
	
	private static final Double TAXA_JUROS_MENSAL = 0.0125;
	
	public ContaPoupanca(Integer numero, Cliente titular ) 
	{
		super(numero, titular);
	}
	
	

	public ContaPoupanca(Integer numero, Double saldo, Cliente titular) 
	{
		super(numero, saldo, titular);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void sacar(Double valor)
	{
		if(valor <= this.getSaldo())
		{
			debitar(valor);
			Transacao transacao = new Transacao(valor, TipoTransacao.SAQUE, this, null);
			historico.add(transacao);
		}
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
		
		if(valor <= this.getSaldo())
		{
			this.debitar(valor);
			contaDestino.depositar(valor);
			Transacao transacao = new Transacao(valor, TipoTransacao.TRANFERENCIA, this, contaDestino);
			historico.add(transacao);
		}
		else
			throw new SaldoInsuficienteException("Saldo Insuficiente");
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
