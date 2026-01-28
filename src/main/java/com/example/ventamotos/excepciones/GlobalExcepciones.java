package com.example.ventamotos.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExcepciones {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> excepciones (MethodArgumentNotValidException ex){
        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("error", errores);

        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(MotoNoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> motoNoEncontradaExcepciones(MotoNoEncontradaException mne){
        Map<String, Object> motoNoEncontradaerrores = new HashMap<>();
        motoNoEncontradaerrores.put("status", HttpStatus.NOT_FOUND.value());
        motoNoEncontradaerrores.put("error", mne.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(motoNoEncontradaerrores);
    }

    @ExceptionHandler(DatosErroneosException.class)
    public ResponseEntity<Map<String, Object>> datosErronesExcepciones(DatosErroneosException dee){
        Map<String, Object> datosErroneos = new HashMap<>();
        datosErroneos.put("status", HttpStatus.BAD_REQUEST.value());
        datosErroneos.put("error", dee.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(datosErroneos);
    }
}
