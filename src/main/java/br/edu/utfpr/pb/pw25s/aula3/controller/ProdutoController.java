package br.edu.utfpr.pb.pw25s.aula3.controller;

import br.edu.utfpr.pb.pw25s.aula3.model.Pedido;
import br.edu.utfpr.pb.pw25s.aula3.model.Pedidoitem;
import br.edu.utfpr.pb.pw25s.aula3.model.Produto;
import br.edu.utfpr.pb.pw25s.aula3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("produto")
public class ProdutoController {

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

    @GetMapping
    private String getAll(Model model) {
        model.addAttribute("produtos", produtoService.findAll());
        return "produto/list";
//        return "index";
    }

    @GetMapping("new")
    private String form(Model model) {
        model.addAttribute("produto", new Produto());
        carregarCombos(model);
        return "produto/form";
    }

    @PostMapping
    private String save(@Valid Produto produto,
                        BindingResult result,
                        Model model,
                        RedirectAttributes attributes) {
        if (result.hasErrors()) {
            carregarCombos(model);
            return "produto/form";
        }
        produtoService.save(produto);
        attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
        return "redirect:/produto";
    }

    @GetMapping("{id}")
    private String form(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("pedidoitem", new Pedidoitem());
        model.addAttribute("produto", produtoService.findOne(id));
        carregarCombos(model);
        return "produto";
    }

    private void carregarCombos(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("pedidoitems", pedidoItemService.findAll());
        model.addAttribute("pedidos", pedidoService.findAll());
    }

    @DeleteMapping("{id}")
    private String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            produtoService.delete(id);
            attributes.addFlashAttribute("sucesso", "Registro removido com sucesso!");
        } catch (Exception ex) {
            attributes.addFlashAttribute("erro", "Falha ao remover o registro.");
        }
        return "redirect:/produto";
    }

}
