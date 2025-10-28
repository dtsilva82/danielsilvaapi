package br.edu.infnet.danielsilvaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.danielsilvaapi.model.domain.Disco;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Integer> {
    

}