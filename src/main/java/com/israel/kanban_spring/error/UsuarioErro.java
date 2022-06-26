package com.israel.kanban_spring.error;

public class UsuarioErro extends RuntimeException{
    public UsuarioErro(String msg){
        super(msg);
    }
}
