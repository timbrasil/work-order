package br.com.timbrasil.operations.models;

public enum StatusAcception {
	
	ACCEPTED("ACEITO"),
	REJECTED("REJEITADO");

	private String nome;

	StatusAcception(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "StatusAcception{" +
				"nome='" + nome + '\'' +
				'}';
	}
}
