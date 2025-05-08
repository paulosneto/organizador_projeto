package com.project.organizer.exceptions;

public class ExceptionCustomError extends RuntimeException {

    final String menssagem;
    final int status;

    public ExceptionCustomError(String menssagem, int status){
        this.menssagem = menssagem;
        this.status = status;
    }
}
