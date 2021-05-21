package br.edu.utfpr.pb.pw25s.aula3.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"pedido", "produto"})
public class PedidoItemPK implements Serializable{
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

}
