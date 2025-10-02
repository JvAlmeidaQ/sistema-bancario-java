package br.com.banco.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.banco.model.Cliente;
import br.com.banco.model.Conta;
import br.com.banco.model.ContaCorrente;
import br.com.banco.model.ContaInvestimento;
import br.com.banco.model.ContaPoupanca;

public class ContaRepository {

	public void salvarContas(Map<Integer, Conta> contas) {
		String path = "contas.csv";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (Map.Entry<Integer, Conta> c : contas.entrySet()) {
				Integer numConta = c.getKey();
				Double saldoConta = c.getValue().getSaldo();
				String cpfTitular = c.getValue().getTitular().getCpf();
				String tipoConta;

				if (c.getValue() instanceof ContaCorrente)
					tipoConta = "CC";

				else if (c.getValue() instanceof ContaPoupanca)
					tipoConta = "CP";

				else
					tipoConta = "CI";

				bw.write(numConta + "," + tipoConta + "," + saldoConta + "," + cpfTitular);
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, Conta> carregarContas(Map<String, Cliente> clientesExistentes) {
		Map<Integer, Conta> contas = new HashMap<>();

		String path = "contas.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			while (line != null) {
				String[] campos = line.split(",");

				Integer numConta = Integer.parseInt(campos[0]);
				String tipo = campos[1];
				Double saldo = Double.parseDouble(campos[2]);
				String cpf = campos[3];

				Conta conta;
				if (tipo.equals("CC")) {
					conta = new ContaCorrente(numConta, saldo, clientesExistentes.get(cpf));

				}

				else if (tipo.equals("CI")) {
					conta = new ContaInvestimento(numConta, saldo, clientesExistentes.get(cpf));
				}

				else
					conta = new ContaPoupanca(numConta, saldo, clientesExistentes.get(cpf));

				contas.put(numConta, conta);
				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return contas;
	}
}
