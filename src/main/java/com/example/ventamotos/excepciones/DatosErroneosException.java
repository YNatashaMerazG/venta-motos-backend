package com.example.ventamotos.excepciones;

public class DatosErroneosException extends RuntimeException{
    public DatosErroneosException(String mensaje){
        super(mensaje);
    }
}
