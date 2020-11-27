package com.aprendec.model;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Conectar {
    
    public DriverManagerDataSource conectar(){
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/prueba?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=America/Lima");
        datasource.setUsername("root");
        datasource.setPassword("root");
        return datasource;
    }
    
}
