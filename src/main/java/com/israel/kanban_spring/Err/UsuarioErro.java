package com.israel.kanban_spring.Err;

public class UsuarioErro extends RuntimeException{
    public UsuarioErro(String msg){
        super(msg);
    }
}
