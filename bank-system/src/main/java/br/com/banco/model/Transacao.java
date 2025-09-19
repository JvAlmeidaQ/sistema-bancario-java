package br.com.banco.model;

import java.time.LocalDate;

public class Transacao {
	private Integer id;
	private LocalDate dataHora;
	private Double valor;
	private Conta contaOrigem;
	private Conta contaDestino;
	
	public Transacao(Integer id, LocalDate dataHora, Double valor, Conta contaOrigem, Conta contaDestino) {
		this.id = id;
		this.dataHora = dataHora;
		this.valor = valor;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", dataHora=" + dataHora + ", valor=" + valor + ", contaOrigem=" + contaOrigem
				+ ", contaDestino=" + contaDestino + "]";
	}
	
	
}
