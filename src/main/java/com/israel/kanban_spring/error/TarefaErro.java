package com.israel.kanban_spring.error;

public class TarefaErro extends RuntimeException {
    public TarefaErro(String msg){
        super(msg);
    }
}
