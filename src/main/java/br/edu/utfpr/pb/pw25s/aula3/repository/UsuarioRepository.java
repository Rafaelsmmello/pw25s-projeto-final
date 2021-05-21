package br.edu.utfpr.pb.pw25s.aula3.repository;

import br.edu.utfpr.pb.pw25s.aula3.model.Permissao;
import br.edu.utfpr.pb.pw25s.aula3.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsername(String username);
	Permissao findByNomeContains(String nome);
}
