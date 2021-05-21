package br.edu.utfpr.pb.pw25s.aula3.controller;

import br.edu.utfpr.pb.pw25s.aula3.model.Categoria;
import br.edu.utfpr.pb.pw25s.aula3.model.Usuario;
import br.edu.utfpr.pb.pw25s.aula3.service.CategoriaService;
import br.edu.utfpr.pb.pw25s.aula3.service.MarcaService;
import br.edu.utfpr.pb.pw25s.aula3.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

//    @GetMapping()
//    private String index() {
//        return "index";
//    }

    @GetMapping("login")
    private String login() {
        return "login";
    }

    @GetMapping("registro")
    private String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }


    @GetMapping("home")
    private String home(Model model) {
        model.addAttribute("categorias", categoriaService.findAllByOrderById());
        model.addAttribute("produtos", produtoService.findByNomeContains("Smartphone"));
        model.addAttribute("notebooks", produtoService.findByNomeContains("Notebook"));
        model.addAttribute("monitores", produtoService.findByNomeContains("Monitor"));
        model.addAttribute("games", produtoService.findByNomeContains("Gamer"));
        return "index";
    }

    @GetMapping("Celulares")
    private String lista1(Model model) {
        model.addAttribute("produtos", produtoService.findByCategoriaId((long) 1));
        return "lista";
    }
    @GetMapping("Monitores")
    private String lista2(Model model) {
        model.addAttribute("produtos", produtoService.findByCategoriaId((long) 2));
        return "lista";
    }
    @GetMapping("Notebooks")
    private String lista3(Model model) {
        model.addAttribute("produtos", produtoService.findByCategoriaId((long) 3));
        return "lista";
    }
    @GetMapping("Eletrodomésticos")
    private String lista4(Model model) {
        model.addAttribute("produtos", produtoService.findByCategoriaId((long) 4));
        return "lista";
    }
    @GetMapping("Games")
    private String lista5(Model model) {
        model.addAttribute("produtos", produtoService.findByCategoriaId((long) 5));
        return "lista";
    }
    @GetMapping("Esportes")
    private String lista6(Model model) {
        model.addAttribute("produtos", produtoService.findByCategoriaId((long) 6));
        return "lista";
    }

    @GetMapping()
    private String index(Model model) {
        model.addAttribute("categorias", categoriaService.findAllByOrderById());
        model.addAttribute("produtos", produtoService.findByNomeContains("Smartphone"));
        model.addAttribute("notebooks", produtoService.findByNomeContains("Notebook"));
        model.addAttribute("monitores", produtoService.findByNomeContains("Monitor"));
        model.addAttribute("games", produtoService.findByNomeContains("Gamer"));
        return "index";
    }



}
