package br.edu.infnet.danielsilvaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.infnet.danielsilvaapi.model.domain.Disco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Integer> {
	
	@Query("SELECT d FROM Disco d WHERE LOWER(d.titulo) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Disco> findByTituloParcial(@Param("termo") String termo);

}