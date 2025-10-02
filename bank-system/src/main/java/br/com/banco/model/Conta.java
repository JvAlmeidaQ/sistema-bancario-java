package br.com.banco.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
	private Integer numero;
	private Double saldo;
	private Cliente titular;
	
	List<Transacao> historico = new ArrayList<>();

	public Conta(Integer numero, Cliente titular) {
		this.numero = numero;
		this.saldo = 0.00;
		this.titular = titular;
	}
	
	

	public Conta(Integer numero, Double saldo, Cliente titular) {
		super();
		this.numero = numero;
		this.saldo = saldo;
		this.titular = titular;
	}



	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Double getSaldo() {
		return saldo;
	}
	
	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	
	public List<Transacao> getHistorico()
	{
		return historico;
	}

	public void depositar(Double valor)
	{
		this.saldo = this.getSaldo() + valor;
	}
	
	protected void creditar(Double valor)
	{
		this.saldo += valor;
	}
	
	protected void debitar(Double valor)
	{
		this.saldo -= valor;
	}
	
	protected void restaurarSaldo(Double valor)
	{
		saldo = valor;
	}
	
	public abstract void sacar(Double valor);
	
	public abstract void transferir(Double valor, Conta contaDestino);
	
	public abstract Double calcularTarifa(Double valor);

	
}

	