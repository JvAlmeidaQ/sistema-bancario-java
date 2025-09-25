package br.com.banco.model;

import br.com.banco.exceptions.SaldoInsuficienteException;

public class ContaCorrente extends Conta 
{
	private static final Double TAXA = 0.01;
	
	private Double limiteChequeEspecial;

	public ContaCorrente(Integer numero, Cliente titular) {
		super(numero, titular);
		this.limiteChequeEspecial = calcularLimiteChequeEspecial();
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
