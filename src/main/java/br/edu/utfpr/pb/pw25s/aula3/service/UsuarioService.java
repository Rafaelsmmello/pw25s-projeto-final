package br.edu.utfpr.pb.pw25s.aula3.service;


import br.edu.utfpr.pb.pw25s.aula3.model.Permissao;
import br.edu.utfpr.pb.pw25s.aula3.model.Usuario;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioService extends CrudService<Usuario, Long>{

//    List<Permissao> findAllPermissao();
    Usuario findByUsername(String username);
}
