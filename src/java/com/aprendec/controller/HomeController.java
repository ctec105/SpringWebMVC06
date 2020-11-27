package com.aprendec.controller;

import com.aprendec.model.Conectar;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    
    private JdbcTemplate jdbcTemplate;
    
    public HomeController(){
        Conectar conn = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(conn.conectar());
    }
    
    @RequestMapping("home.htm")
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView();
        
        String sql = "select * from usuarios order by id desc;";
        List datos = jdbcTemplate.queryForList(sql);
        mav.setViewName("home");
        mav.addObject("datos", datos);
        return mav;
    }
    
}
