package br.com.timbrasil.operations.models;

public enum Technology {

	GSM("2G"),
	WCDMA("3G"),
	LTE("4G"),
	MW("MW"),
	PTN("PTN"),
	SMALLCELL("SmallCell"),
	AGGREGATOR("Agregador");
	
	private String nome;
	
	private Technology(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
