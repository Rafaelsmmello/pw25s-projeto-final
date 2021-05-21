package br.edu.utfpr.pb.pw25s.aula3.service.impl;

import br.edu.utfpr.pb.pw25s.aula3.model.Pedidoitem;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;
import br.edu.utfpr.pb.pw25s.aula3.repository.PedidoItemRepository;
import br.edu.utfpr.pb.pw25s.aula3.repository.ProdutoRepository;
import br.edu.utfpr.pb.pw25s.aula3.service.PedidoItemService;
import br.edu.utfpr.pb.pw25s.aula3.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoItemServiceImpl extends CrudServiceImpl<Pedidoitem, Long> implements PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Override
    protected JpaRepository<Pedidoitem, Long> getRepository() {
        return pedidoItemRepository;
    }

}
