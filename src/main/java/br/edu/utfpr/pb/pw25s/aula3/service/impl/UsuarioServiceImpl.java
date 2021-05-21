package br.edu.utfpr.pb.pw25s.aula3.service.impl;

import br.edu.utfpr.pb.pw25s.aula3.model.Permissao;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;
import br.edu.utfpr.pb.pw25s.aula3.model.Usuario;
import br.edu.utfpr.pb.pw25s.aula3.repository.UsuarioRepository;
import br.edu.utfpr.pb.pw25s.aula3.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, Long>
		implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	protected JpaRepository<Usuario, Long> getRepository() {
		return usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return usuario;
	}

	@Override
	public Usuario findByUsername(String username) {
		return this.usuarioRepository.findByUsername(username);
	}
}
