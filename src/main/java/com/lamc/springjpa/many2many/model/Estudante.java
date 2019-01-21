package com.lamc.springjpa.many2many.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
public class Estudante {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nome;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "estudante_grade", joinColumns = @JoinColumn(name = "id_estudante", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "id_grade", referencedColumnName = "id"))
	private Set<Grade> grade;
	
	public Estudante(){
		
	}

	public Estudante(String nome) {
		this.nome = nome;
	}

	public Estudante(String nome, Set<Grade> grade) {
		this.nome = nome;
		this.grade = grade;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Grade> getGrade() {
		return grade;
	}

	public void setGrade(Set<Grade> grade) {
		this.grade = grade;
	}

	@Override
	public String toString(){
		String info = "";
		JSONObject jsonInfo = new JSONObject();
		jsonInfo.put("nome",this.nome);
		JSONArray subArray = new JSONArray();
		
		this.grade.forEach(grade->{
			JSONObject subJson = new JSONObject();
			subJson.put("nome", grade.getNome());
			subArray.put(subJson);
		});
		
		jsonInfo.put("grade", subArray);
		info = jsonInfo.toString();
		return info;
	}
}
