package br.com.banco.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
	private Integer numero;
	private Double saldo;
	private Cliente titular;
	
	List<Transacao> historico;

	public Conta(Integer numero, Cliente titular) {
		this.numero = numero;
		this.saldo = 0.00;
		this.titular = titular;
		this.historico = new ArrayList<>();
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

	public abstract void depositar(Double valor);
	
	public abstract void sacar(Double Valor);
	
	public void transferir(Double Valor, Conta contaDestino)
	{
		this.sacar(Valor);
		contaDestino.depositar(Valor);
	}
	
	public abstract Double calcularTarifa();
	
	public List<Transacao> getHistorico()
	{
		return historico;
	}

	
}

	