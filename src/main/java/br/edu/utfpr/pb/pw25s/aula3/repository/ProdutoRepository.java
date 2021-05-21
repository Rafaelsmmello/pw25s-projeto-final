package br.edu.utfpr.pb.pw25s.aula3.repository;

import br.edu.utfpr.pb.pw25s.aula3.model.Categoria;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByNomeContains(String nome);
    List<Produto> findByCategoriaId(Long id);
}












