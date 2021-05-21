package br.edu.utfpr.pb.pw25s.aula3.service;

import br.edu.utfpr.pb.pw25s.aula3.model.Categoria;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;

import java.util.List;

public interface ProdutoService extends CrudService<Produto, Long> {
    List<Produto> findByNomeContains(String nome);
    List<Produto> findByCategoriaId(Long id);

}
