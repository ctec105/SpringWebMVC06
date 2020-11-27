package com.aprendec.controller;

import com.aprendec.model.Conectar;
import com.aprendec.model.UsuarioValidacion;
import com.aprendec.model.Usuarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("edit.htm")
public class EditController {

    private UsuarioValidacion usuarioValidacion;
    private JdbcTemplate jdbcTemplate;

    public EditController() {
        usuarioValidacion = new UsuarioValidacion();
        Conectar conn = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(conn.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        Usuarios datos = this.selectUsuario(id);
        
        mav.setViewName("edit");
        mav.addObject("usuarios", new Usuarios(id, datos.getNombre(), datos.getCorreo(), datos.getTelefono()));
        
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("usuarios") Usuarios u, BindingResult result, SessionStatus status, HttpServletRequest request) {
        
        this.usuarioValidacion.validate(u, result);
        
        if (result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            int id = Integer.parseInt(request.getParameter("id"));
            Usuarios datos = this.selectUsuario(id);
        
            mav.setViewName("edit");
            mav.addObject("usuarios", new Usuarios(id, datos.getNombre(), datos.getCorreo(), datos.getTelefono()));
            return mav;
        }else{
            int id = Integer.parseInt(request.getParameter("id"));
            this.jdbcTemplate.update("update usuarios set nombre = ?, correo = ?, telefono = ? where id = ?", 
                    u.getNombre(), u.getCorreo(), u.getTelefono(), id);
            return new ModelAndView("redirect:/home.htm");
        }
    }

    private Usuarios selectUsuario(int id) {
        final Usuarios usuario = new Usuarios();
        String query = "select * from usuarios where id = '" + id + "'";
        
        return (Usuarios) this.jdbcTemplate.query(query, new ResultSetExtractor<Usuarios>(){
            @Override
            public Usuarios extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()){
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setTelefono(rs.getString("telefono"));
                }
                return usuario;
            }
        });
        
        
    }

}
