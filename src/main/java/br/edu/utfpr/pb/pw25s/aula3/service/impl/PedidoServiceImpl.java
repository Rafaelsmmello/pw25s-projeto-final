package br.edu.utfpr.pb.pw25s.aula3.service.impl;

import br.edu.utfpr.pb.pw25s.aula3.model.Pedido;
import br.edu.utfpr.pb.pw25s.aula3.model.Pedidoitem;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;
import br.edu.utfpr.pb.pw25s.aula3.repository.PedidoItemRepository;
import br.edu.utfpr.pb.pw25s.aula3.repository.PedidoRepository;
import br.edu.utfpr.pb.pw25s.aula3.service.PedidoItemService;
import br.edu.utfpr.pb.pw25s.aula3.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class PedidoServiceImpl extends CrudServiceImpl<Pedido, Long> implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    protected JpaRepository<Pedido, Long> getRepository() {
        return pedidoRepository;
    }

    public Pedido salvarProduto(Pedido pedido){

//          pedido.setDataCriacao(new Date());
        return pedidoRepository.save(pedido);

    }

    @Override
    public List<Pedido> findByIdUsuario(Long id) {
        return this.pedidoRepository.findByIdUsuario(id);
    }
}
