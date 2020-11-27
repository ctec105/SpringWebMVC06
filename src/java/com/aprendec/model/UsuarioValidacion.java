package com.aprendec.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UsuarioValidacion implements Validator {
    
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;
    

    @Override
    public boolean supports(Class<?> type) {
        return Usuarios.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Usuarios usuario = (Usuarios) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre", "El campo nombre es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "required.correo", "El campo correo es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required.telefono", "El campo teléfono es obligatorio");
        
        if (!(usuario.getCorreo()!=null && usuario.getCorreo().isEmpty())){
            this.pattern = Pattern.compile(EMAIL_PATTERN);
            this.matcher = pattern.matcher(usuario.getCorreo());
            if (!matcher.matches()){
                errors.rejectValue("correo", "correo.incorrect", "El correo " + usuario.getCorreo() + " no es valido");
            }
            
        }
    }
}
