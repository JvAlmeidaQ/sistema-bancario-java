package br.com.banco.model;

import br.com.banco.exceptions.SaldoInsuficienteException;

public class ContaCorrente extends Conta implements Tributavel
{
	private static final Double TAXA = 0.01;
	
	private Double limiteChequeEspecial;

	public ContaCorrente(Integer numero, Cliente titular) {
		super(numero, titular);
		this.limiteChequeEspecial = calcularLimiteChequeEspecial();
	}
	
	public ContaCorrente(Integer numero, Double saldo, Cliente titular)
	{
		super(numero, saldo, titular);
	}

	private double calcularLimiteChequeEspecial() 
	{
        double limite = getSaldo() * 0.3;

        if (limite < 500) {
            limite = 500;
        } else if (limite > 5000) {
            limite = 5000;
        }

        return limite;
    }
	
	public double getLimiteChequeEspecial() 
	{
        return limiteChequeEspecial;
    }
	
	@Override
	public void sacar(Double valor) 
	{
		Double valorComTaxa = valor + this.calcularTarifa(valor);
		
		if(valorComTaxa <= this.getSaldo() + limiteChequeEspecial)
		{
			this.debitar(valorComTaxa);
			Transacao transacao = new Transacao(valorComTaxa, TipoTransacao.SAQUE, this, null);
			historico.add(transacao);
		}
		else
			throw new SaldoInsuficienteException("Saldo Insuficiente");
	}

	@Override
	public void transferir(Double valor, Conta contaDestino) {
		
		Double valorComTaxa = valor + this.calcularTarifa(valor);
		
		if(valorComTaxa <= this.getSaldo() + limiteChequeEspecial)
		{
			this.debitar(valorComTaxa);
			contaDestino.depositar(valor);
			Transacao transacao = new Transacao(valor, TipoTransacao.TRANFERENCIA, this, contaDestino);
			historico.add(transacao);
		}
		else
			throw new SaldoInsuficienteException("Saldo Insuficiente");
	}

	@Override
	public Double calcularTarifa(Double valor) {
		return valor * TAXA;
	}

	@Override
	public Double calcularImpostos() {
		// TODO Auto-generated method stub
		return this.getSaldo()*TAXA;
	}
	
}
