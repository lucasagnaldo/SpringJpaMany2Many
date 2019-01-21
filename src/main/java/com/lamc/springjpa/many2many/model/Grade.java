package com.lamc.springjpa.many2many.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
public class Grade {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
	private String nome;
	
	@ManyToMany(mappedBy = "grade")
	private Set<Estudante> estudante;
	
    public Grade(){
    	
    }
    
	public Grade(String nome) {
		super();
		this.nome = nome;
	}


	public Grade(String nome, Set<Estudante> estudante) {
		super();
		this.nome = nome;
		this.estudante = estudante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Estudante> getEstudante() {
		return estudante;
	}

	public void setEstudante(Set<Estudante> estudante) {
		this.estudante = estudante;
	}

	@Override
	public String toString(){
		String info = "";
		JSONObject jsonInfo = new JSONObject();
		
		jsonInfo.put("nome",this.nome);
		JSONArray estudanteArray = new JSONArray();
		
		if (this.estudante != null && estudante.size() > 0) {
			this.estudante.forEach(estudante -> {
				JSONObject subJson = new JSONObject();
				subJson.put("nome", estudante.getNome());
				estudanteArray.put(subJson);
			});
		}
		
		jsonInfo.put("estudante", estudanteArray);
		info = jsonInfo.toString();
		return info;
	}
}
