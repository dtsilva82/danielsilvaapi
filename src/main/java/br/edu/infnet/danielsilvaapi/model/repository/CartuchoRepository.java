package br.edu.infnet.danielsilvaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.danielsilvaapi.model.domain.Cartucho;

@Repository
public interface CartuchoRepository extends JpaRepository<Cartucho, Integer> {
    
   
}