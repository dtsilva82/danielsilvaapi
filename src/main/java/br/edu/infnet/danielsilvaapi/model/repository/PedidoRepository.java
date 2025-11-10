package br.edu.infnet.danielsilvaapi.model.repository;

import br.edu.infnet.danielsilvaapi.model.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
}