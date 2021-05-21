package br.edu.utfpr.pb.pw25s.aula3.controller;

import br.edu.utfpr.pb.pw25s.aula3.model.*;
import br.edu.utfpr.pb.pw25s.aula3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.Console;
import java.time.LocalDate;

@Controller
@RequestMapping("pedido")
public class PedidoController {

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


    //exibir pedidos do usuario, filtrando pelo usuario findbyUserId
//    @GetMapping
//    public String list(Model model) {
//        model.addAttribute("pedidos", pedidoService.findAll());
//        return "pedido/list";
//    }

//    @GetMapping("new")
//    private String form(Model model) {
//        model.addAttribute("produto", new Produto());
//        carregarCombos(model);
//        return "produto/form";
//    }

    private void carregarCombos(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("pedidoItems", pedidoItemService.findAll());
        model.addAttribute("pedidos", pedidoService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
    }

    @PostMapping("/add")
    public String save(@Valid Pedido pedido,
                       BindingResult result,
                       RedirectAttributes attributes, Model model) {

        try {
            if (result.hasErrors()) {
                return "redirect:/home";
            }
            pedido.setDatapedido(LocalDate.now());
            pedido.setTipofrete("Delivery");
//          pedido.setValorfrete(12.21);
            pedido.setDataalteracao(LocalDate.now());
            attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
            pedidoService.save(pedido);


//            PedidoItemPK pedidoItemPK = new PedidoItemPK();
//            pedidoItemPK.setPedido(pedido);
//            pedidoItemPK.setProduto(produtoService.findOne((long) 1));
//            Pedidoitem pedidoitem = new Pedidoitem();
//            pedidoitem.setId(pedidoItemPK);
//            pedidoitem.setQuantidade(1);
//            pedidoitem.setImagem("aaa");
//            pedidoitem.setValor(12.22);
//            pedidoItemService.save(pedidoitem);

            return "redirect:/carrinho";
        } catch (Exception e) {
            return "redirect:/home";
        }
    }
    @PostMapping("/add/produtoitem")
    public String saveprodutoitem(@Valid Pedido pedido,
                       BindingResult result,
                       RedirectAttributes attributes, Model model) {

        try {
            if (result.hasErrors()) {
                return "redirect:/home";
            }
            pedido.setDatapedido(LocalDate.now());
            pedido.setTipofrete("Delivery");
//          pedido.setValorfrete(12.21);
            pedido.setDataalteracao(LocalDate.now());
            attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
            pedidoService.save(pedido);


//            PedidoItemPK pedidoItemPK = new PedidoItemPK();
//            pedidoItemPK.setPedido(pedido);
//            pedidoItemPK.setProduto(produtoService.findOne((long) 1));
//            Pedidoitem pedidoitem = new Pedidoitem();
//            pedidoitem.setId(pedidoItemPK);
//            pedidoitem.setQuantidade(1);
//            pedidoitem.setImagem("aaa");
//            pedidoitem.setValor(12.22);
//            pedidoItemService.save(pedidoitem);

            return "redirect:/carrinho";
        } catch (Exception e) {
            return "redirect:/home";
        }
    }
//    @PostMapping("/pedidoitem/addNew")
//    private String addNew(@Valid Pedidoitem pedidoitem,
//                        BindingResult result,
//                        RedirectAttributes attributes,
//                        Model model
//    ) {
//
//        if (result.hasErrors()) {
//            model.addAttribute("pedidoitem", pedidoitem);
//            return "pedidoitem";
//        }
//
//        pedidoItemService.save(pedidoitem);
//        attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
//        return "redirect:/carrinho";
//    }

    @DeleteMapping("pedidoitem/{id}")
    private String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            pedidoItemService.delete(id);
            attributes.addFlashAttribute("sucesso", "Registro removido com sucesso!");
        } catch (Exception ex) {
            attributes.addFlashAttribute("erro", "Falha ao remover o registro.");
        }
        return "redirect:/carrinho";
    }




}
