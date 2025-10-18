package br.com.banco.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.banco.util.DateTimeUtil;
import br.com.banco.util.IdGenerator;

public class Transacao {
	private Integer id;
	private LocalDateTime dataHora;
	private Double valor;
	private TipoTransacao tipo;
	private Conta contaOrigem;
	private Conta contaDestino;
	
	public Transacao(Double valor, TipoTransacao tipo, Conta contaOrigem, Conta contaDestino) {
		this.id = IdGenerator.geraId();
		this.dataHora = DateTimeUtil.dataAtual();
		this.valor = valor;
		this.tipo = tipo;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public Double getValor() {
		return valor;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", dataHora=" + dataHora + ", valor=" + valor + ", contaOrigem=" + contaOrigem
				+ ", contaDestino=" + contaDestino + "]";
	}
	
	
}
