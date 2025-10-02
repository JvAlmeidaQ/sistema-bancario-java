package br.com.banco.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.banco.model.Cliente;

public class ClienteRepository {

	public void salvarClientes(Map<String, Cliente> clientes) {
		String path = "clientes.csv";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (Map.Entry<String, Cliente> c : clientes.entrySet()) {
				bw.write(c.getKey() + "," + c.getValue().getId() + "," + c.getValue().getNome());
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Cliente> carregarClientes() {

		Map<String, Cliente> clientes = new HashMap<>();

		String path = "clientes.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] campos = line.split(",");
				
				String cpf = campos[1];
				Integer id = Integer.parseInt(campos[1]);
				String nome = campos[2];

				clientes.put(cpf, new Cliente(id, nome, cpf));
				br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return clientes;
	}

}
