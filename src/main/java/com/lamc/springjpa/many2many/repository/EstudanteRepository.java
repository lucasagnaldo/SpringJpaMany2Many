package com.lamc.springjpa.many2many.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamc.springjpa.many2many.model.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Integer>{
}
