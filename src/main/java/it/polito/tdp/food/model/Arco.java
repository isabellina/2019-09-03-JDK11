package it.polito.tdp.food.model;

public class Arco {
	
	private String nomePorzione1;
	private String nomePorzione2;
	private int peso;
	
	public Arco(String nomePorzione1, String nomePorzione2, int peso) {
		
		this.nomePorzione1 = nomePorzione1;
		this.nomePorzione2 = nomePorzione2;
		this.peso = peso;
	}

	public String getNomePorzione1() {
		return nomePorzione1;
	}

	public void setNomePorzione1(String nomePorzione1) {
		this.nomePorzione1 = nomePorzione1;
	}

	public String getNomePorzione2() {
		return nomePorzione2;
	}

	public void setNomePorzione2(String nomePorzione2) {
		this.nomePorzione2 = nomePorzione2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	

}
