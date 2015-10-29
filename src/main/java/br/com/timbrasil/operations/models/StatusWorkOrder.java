package br.com.timbrasil.operations.models;

public enum StatusWorkOrder {

	ACCEPTED("ACEITO"),
	REJECTED("REJEITADO"),
	WORKING("TRABALHANDO"),
	CREATE("CRIADO"),
	REATRIBUTION("REATRIBUIDO");

	private String name;

    StatusWorkOrder(String name) {
        this.name = name;
    }

    public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "StatusWorkOrder{" +
				"name='" + name + '\'' +
				'}';
	}
}
