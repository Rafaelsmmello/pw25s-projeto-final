package br.edu.utfpr.pb.pw25s.aula3.controller;

import br.edu.utfpr.pb.pw25s.aula3.model.Pedido;
import br.edu.utfpr.pb.pw25s.aula3.model.Pedidoitem;
import br.edu.utfpr.pb.pw25s.aula3.model.Permissao;
import br.edu.utfpr.pb.pw25s.aula3.model.Usuario;
import br.edu.utfpr.pb.pw25s.aula3.service.CategoriaService;
import br.edu.utfpr.pb.pw25s.aula3.service.PedidoService;
import br.edu.utfpr.pb.pw25s.aula3.service.PermissaoService;
import br.edu.utfpr.pb.pw25s.aula3.service.UsuarioService;
import br.edu.utfpr.pb.pw25s.aula3.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PermissaoService permissaoService;
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public String save(@Valid Usuario usuario,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if ( result.hasErrors() ) {
            model.addAttribute("usuario", usuario);
            return "/login";
        }

        Set<Permissao> permissaos = new HashSet<>();
        permissaos.add(permissaoService.findOne((long) 2));
        usuario.setPermissoes(permissaos);

//        Usuario usuario2 = usuarioService.findOne((long) 2);
//
//        usuario.setPermissoes(usuario2.getPermissoes());

        usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));

        usuarioService.save(usuario);


        attributes.addFlashAttribute("sucesso",
                "Usuario salvo com sucesso!");
        return "redirect:/login";
    }

    @GetMapping("{id}")
    private String usuario(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pedido", pedidoService.findByIdUsuario(id));
        model.addAttribute("usuario", usuarioService.findOne(id));
//        pegausuario(model);
        return "contausuario";
    }
//    public void pegausuario(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//        model.addAttribute("username", usuarioService.findByUsername(name));
////        usuarioService.findOne();
//    }
//    INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (2, 2);
}
