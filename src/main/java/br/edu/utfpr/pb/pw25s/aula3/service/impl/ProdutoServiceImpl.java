package br.edu.utfpr.pb.pw25s.aula3.service.impl;

import br.edu.utfpr.pb.pw25s.aula3.model.Categoria;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;
import br.edu.utfpr.pb.pw25s.aula3.repository.ProdutoRepository;
import br.edu.utfpr.pb.pw25s.aula3.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, Long> implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    protected JpaRepository<Produto, Long> getRepository() {
        return produtoRepository;
    }

    @Override
    public List<Produto> findByNomeContains(String nome) {
        return this.produtoRepository.findByNomeContains(nome);
    }

    @Override
    public List<Produto> findByCategoriaId(Long id) {
        return this.produtoRepository.findByCategoriaId(id);
    }
}
