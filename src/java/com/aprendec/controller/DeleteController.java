package com.aprendec.controller;

import com.aprendec.model.Conectar;
import com.aprendec.model.UsuarioValidacion;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteController {

    private UsuarioValidacion usuarioValidacion;
    private JdbcTemplate jdbcTemplate;

    public DeleteController() {
        Conectar conn = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(conn.conectar());
    }

    @RequestMapping("delete.htm")
    public ModelAndView home(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        this.jdbcTemplate.update("delete from usuarios where id = ?", id);
        return new ModelAndView("redirect:/home.htm");
    }
}
