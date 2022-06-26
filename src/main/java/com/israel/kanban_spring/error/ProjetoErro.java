package com.israel.kanban_spring.error;

public class ProjetoErro extends RuntimeException {
    public ProjetoErro(String msg){
        super(msg);
    }
}
