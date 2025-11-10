package br.edu.infnet.danielsilvaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.edu.infnet.danielsilvaapi.model.domain.Cartucho;
import org.springframework.stereotype.Repository;

@Repository
public interface CartuchoRepository extends JpaRepository<Cartucho, Integer> {
 
	@Query("SELECT c FROM Cartucho c WHERE LOWER(c.titulo) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Cartucho> findByTituloParcial(@Param("termo") String termo);
   
}