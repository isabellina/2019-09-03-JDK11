package it.polito.tdp.food.model;

public class PorzionePeso {

	private String nomePorzione;
	private int peso;
	
	public PorzionePeso(String nomePorzione, int peso) {
		super();
		this.nomePorzione = nomePorzione;
		this.peso = peso;
	}

	public String getNomePorzione() {
		return nomePorzione;
	}

	public void setNomePorzione(String nomePorzione) {
		this.nomePorzione = nomePorzione;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "PorzionePeso [nomePorzione=" + nomePorzione + ", peso=" + peso + "]";
	}
	
	
}
