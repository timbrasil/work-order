package br.com.timbrasil.operations.models;

public enum Region {
	
	TSP("TIM São Paulo"),
	TRL("TIM Rio e Leste"),
	TSCO("TIM Sul e Centro-oeste"),
	TNNO("TIM Norte e Nordeste"),
	THQ("TIM Headquarter");
	
	private String nome;

	private Region(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
