package br.com.banco.model;
	
public class ContaInvestimento extends Conta {

	private static final Double TARIFA_RESGATE = 0.002;

	public ContaInvestimento(Integer numero, Cliente titular) {
		super(numero, titular);
	}

	
	
	public ContaInvestimento(Integer numero, Double saldo, Cliente titular)
	{
		super(numero, saldo, titular);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void sacar(Double valor) {
	    throw new UnsupportedOperationException("Saques diretos não são permitidos em Conta Investimento. Utilize a transferência (resgate).");
	}

	@Override
	public void transferir(Double valor, Conta contaDestino) {
		
		if (!contaDestino.getTitular().equals(this.getTitular()))
		{
	        throw new IllegalArgumentException("Transferências da poupança só são permitidas para contas do mesmo titular.");
	    }
		
		if (!(contaDestino instanceof ContaCorrente)) {
	        throw new IllegalArgumentException("Transferências da poupança só são permitidas para uma Conta Corrente.");
	    }
		
		Double valorComTarifa = valor + this.calcularTarifa(valor);
		this.debitar(valorComTarifa);
		contaDestino.debitar(valor);
		
	}

	@Override
	public Double calcularTarifa(Double valor) {
		return valor * TARIFA_RESGATE;
	}

}
