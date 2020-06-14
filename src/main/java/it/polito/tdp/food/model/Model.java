package it.polito.tdp.food.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private Graph<String, DefaultWeightedEdge> grafo ;
	private List<String> listaPorzioni;
	private FoodDao dao ;
	private String verticePartenza;
	private List<String> listaSoluzione;
	private int lunghezza;
	private int max;
	
	public Model() {
		
		this.dao = new FoodDao();
	}
	
	public List<String> tendina(int c){
		List<String> perTendina = new LinkedList<String>(this.dao.getPorzioniCalorie(c));
		return perTendina;
	}
	
	
	public void creaGrafo(int c) {
		
	 this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	 this.listaPorzioni = new LinkedList<String>(this.dao.getPorzioniCalorie(c));
	 Graphs.addAllVertices(this.grafo, listaPorzioni);
	  for(Arco a : this.dao.getArchi()) {
		  if(this.grafo.containsVertex(a.getNomePorzione1()) && this.grafo.containsVertex(a.getNomePorzione2())) {
			  DefaultWeightedEdge edge = this.grafo.getEdge(a.getNomePorzione1(), a.getNomePorzione2());
			  if(edge==null) {
				 this.grafo.addEdge(a.getNomePorzione1(), a.getNomePorzione2());
				  this.grafo.setEdgeWeight(a.getNomePorzione1(), a.getNomePorzione2(), a.getPeso());
			  }
			  else {
				  int old = (int) this.grafo.getEdgeWeight(edge);
				  int nuovo = a.getPeso();
				  this.grafo.setEdgeWeight(edge, nuovo);
			  }
		  }
	  }
	  System.out.println(this.grafo);
	 
	 
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	
	public List<PorzionePeso> getVerticiConnessi(String s){
		List<PorzionePeso> l = new LinkedList<PorzionePeso>();
		List<String> ltemp = new LinkedList<String>(Graphs.neighborListOf(this.grafo, s));
		for(String d : ltemp) {
			DefaultWeightedEdge edge = this.grafo.getEdge(s, d);
			int peso = (int) this.grafo.getEdgeWeight(edge);
			l.add(new PorzionePeso(d,peso));
		}
		return l;
		
		
	}
	
	public List<String> getCammino(String p, int lunghezza){
		this.verticePartenza = p;
		this.listaSoluzione = new LinkedList<String>();
		this.lunghezza = lunghezza;
		this.max =-1;
		List<String> parziale =new LinkedList<String>();
		return this.listaSoluzione;
		
	}
	
	private void recursive(List<String> parziale, int livello) {
	  if(parziale.size()> lunghezza) {
		  return;
	  }
	  if(parziale.size()== lunghezza && this.getMax()>max ) {
		  this.max = this.getMax();
		  this.listaSoluzione = new LinkedList<String>(parziale);
				  
	  }
	  
	  parziale.add(verticePartenza);
	  
	  
	  
		
	}
	
	public int getMax() {
		return 0;
	}
	
	
	
	
	
	
	
	
	
}
