package com.example.ventamotos.excepciones;

public class MotoNoEncontradaException extends RuntimeException{
    public MotoNoEncontradaException(String mensaje){
        super(mensaje);
    }
}
