package br.edu.utfpr.pb.pw25s.aula3.controller;

import br.edu.utfpr.pb.pw25s.aula3.model.Pedido;
import br.edu.utfpr.pb.pw25s.aula3.model.Pedidoitem;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;
import br.edu.utfpr.pb.pw25s.aula3.model.Usuario;
import br.edu.utfpr.pb.pw25s.aula3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarrinhoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/confirmacao")
    private String confirmacao(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("pedidoitem", new Pedidoitem());
        carregarCombos(model);
        pegausuario(model);
        return "confirmacao";
    }

    @GetMapping("/carrinho")
    private String carrinho(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("pedidoitem", new Pedidoitem());
        carregarCombos(model);
        return "carrinho";
    }

    private void carregarCombos(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("pedidoItems", pedidoItemService.findAll());
        model.addAttribute("pedidos", pedidoService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
    }
    public void pegausuario(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("username", usuarioService.findByUsername(name));
//        usuarioService.findOne();
    }
}
