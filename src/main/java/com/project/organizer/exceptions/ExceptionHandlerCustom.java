package com.project.organizer.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerCustom  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExceptionCustomError.class)
    public ProblemDetail falha(ExceptionCustomError e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(e.status);
        problemDetail.setDetail(e.menssagem);

        return  problemDetail;
    }
    // Exceção usada para retornar mensagem caso o
    // objeto a ser procurado no banco ja exista
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> entradaDuplicada(String menssagem){
        ExceptionDTO dto = new ExceptionDTO(menssagem, "400");
        return ResponseEntity.badRequest().body(dto);
    }

    // Exceção disparada quando o objeto procurado nao for encontrado no banco
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> naoEncontrado(String message){
        ExceptionDTO dto = new ExceptionDTO(message , "404");
        return  ResponseEntity.notFound().build();
    }

    // Exceção usada para disparar erro interno de servidor para requisição
    @ExceptionHandler(Exception.class)
    //public ResponseEntity excecaoGenerica(String message){
    public ResponseEntity<String> excecaoGenerica(Exception ex){
        //ExceptionDTO dto = new ExceptionDTO(message, "500");
        //return  ResponseEntity.internalServerError().body(dto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
