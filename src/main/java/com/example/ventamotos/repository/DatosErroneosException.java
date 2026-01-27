package com.example.ventamotos.repository;

public class DatosErroneosException extends RuntimeException{
    public DatosErroneosException(String mensaje){
        super(mensaje);
    }
}
