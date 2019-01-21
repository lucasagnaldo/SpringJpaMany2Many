package com.lamc.springjpa.many2many;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lamc.springjpa.many2many.model.Estudante;
import com.lamc.springjpa.many2many.model.Grade;
import com.lamc.springjpa.many2many.repository.EstudanteRepository;
import com.lamc.springjpa.many2many.repository.GradeRepository;


@SpringBootApplication
public class SpringJpaMany2ManyApplication implements CommandLineRunner {
	
	@Autowired
	EstudanteRepository estudanteRepository;
	
	@Autowired
	GradeRepository gradeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaMany2ManyApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... arg0) throws Exception {
		Estudante joao = new Estudante("João");
		Estudante maria = new Estudante("Maria");
		
		Grade matematica = new Grade("Matemática");
		Grade computacao = new Grade("Computação");
		
		Set<Grade> grade = new HashSet<Grade>();
		grade.add(matematica);
		grade.add(computacao);
		
		joao.setGrade(grade);
		maria.setGrade(grade);
		
		estudanteRepository.save(joao);
		estudanteRepository.save(maria);
		
		
		Set<Estudante> estudante = new HashSet<Estudante>();
		estudante.add(joao);
		estudante.add(maria);
		
		matematica.setEstudante(estudante);
		computacao.setEstudante(estudante);
		
		gradeRepository.save(matematica);
		gradeRepository.save(computacao);
		
		List<Estudante> estudantes = estudanteRepository.findAll();
		List<Grade> grades = gradeRepository.findAll();
		
		System.out.println("========================== Quantidade de grades e estudantes: ==========================");
		System.out.println("Estudantes: " + estudantes.size());
		System.out.println("Grades:" + grades.size());
		
		
		System.out.println("========================== Informações dos estudantes: ==========================");
		estudantes.forEach(student->System.out.println(student.toString()));
		
		System.out.println("========================== Informações das grades: ==========================");
		grades.forEach(subject->System.out.println(subject.toString()));
		
		System.out.println("==============================================================================");
	}
}
