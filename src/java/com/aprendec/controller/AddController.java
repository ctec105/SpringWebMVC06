package com.aprendec.controller;

import com.aprendec.model.Conectar;
import com.aprendec.model.UsuarioValidacion;
import com.aprendec.model.Usuarios;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("add.htm")
public class AddController {

    private UsuarioValidacion usuarioValidacion;
    private JdbcTemplate jdbcTemplate;

    public AddController() {
        usuarioValidacion = new UsuarioValidacion();
        Conectar conn = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(conn.conectar());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        mav.addObject("usuarios", new Usuarios());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("usuarios") Usuarios u, BindingResult result, SessionStatus status) {
        
        this.usuarioValidacion.validate(u, result);
        
        if (result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("add");
            mav.addObject("usuarios", new Usuarios());
            return mav;
        }else{
            this.jdbcTemplate.update("insert into usuarios (nombre, correo, telefono) values (?,?,?)", 
                    u.getNombre(), u.getCorreo(), u.getTelefono());
            return new ModelAndView("redirect:/home.htm");
        }
    }

}
