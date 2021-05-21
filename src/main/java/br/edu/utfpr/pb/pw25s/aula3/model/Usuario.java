package br.edu.utfpr.pb.pw25s.aula3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Usuario implements Serializable,
        UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 255, nullable = false)
	private String nome;
	
	@Column(length = 100, nullable = false)
	private String username;
	
	@Column(length = 1024, nullable = false)
	private String password;

	@Column(length = 100, nullable = false)
	private String cidade;

//	@Column(length = 100)
//	private String estado;

	@Column(length = 254, nullable = false)
	private String endereco;

	@Column(length = 100, nullable = false)
	private String telefone;

	@Column(length = 10, nullable = false)
	private String cep;

	@ManyToMany(cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private Set<Permissao> permissoes;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		list.addAll(permissoes);
		return list;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}




