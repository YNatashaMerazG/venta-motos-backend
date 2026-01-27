package com.example.ventamotos.repository;

public class MotoNoEncontradaException extends RuntimeException{
    public MotoNoEncontradaException(String mensaje){
        super(mensaje);
    }
}
