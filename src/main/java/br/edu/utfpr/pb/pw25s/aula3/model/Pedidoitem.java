package br.edu.utfpr.pb.pw25s.aula3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.money.DecimalMaxValidatorForMonetaryAmount;
import org.springframework.format.number.money.MonetaryAmountFormatter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import java.awt.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Pedidoitem implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedidoItemPK id;

	@Column(nullable = false)
	private String imagem;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private Double valor;

}
