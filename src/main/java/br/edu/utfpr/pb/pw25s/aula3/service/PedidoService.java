package br.edu.utfpr.pb.pw25s.aula3.service;

import br.edu.utfpr.pb.pw25s.aula3.model.Pedido;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;

import java.util.List;

public interface PedidoService extends CrudService<Pedido, Long> {
    List<Pedido> findByIdUsuario(Long id);
}
