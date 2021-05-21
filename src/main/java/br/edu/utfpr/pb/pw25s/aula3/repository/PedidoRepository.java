package br.edu.utfpr.pb.pw25s.aula3.repository;

import br.edu.utfpr.pb.pw25s.aula3.model.Pedido;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    List<Pedido> findByIdUsuario(Long id);
}












