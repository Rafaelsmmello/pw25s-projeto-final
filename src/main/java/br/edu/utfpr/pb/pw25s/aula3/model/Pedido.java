package br.edu.utfpr.pb.pw25s.aula3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotEmpty
//	@Column
//	private Double valortotal;

	@Column
	public Double valortotal;

	@Column
	private String tipofrete;

	@Column
	private String tipopagamento;

	@Column
	private LocalDate datapedido;

	@Column
	private LocalDate dataalteracao;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id.pedido")
	private List<Pedidoitem> compraprodutos;

	@Column
	private Long idUsuario;

//	@NotEmpty
//	@Column(length = 11, nullable = false)
//	private Cliente idCliente;
//
//	@NotEmpty
//	@Column(length = 50, nullable = false)
//	private String Situacao;
//


public double getValorTotal() {
	return compraprodutos.stream()
			.map(cp -> cp.getValor() * cp.getQuantidade())
			.collect(Collectors.summingDouble(Double::doubleValue));
}
}
