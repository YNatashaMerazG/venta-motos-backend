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
        Map<String, Object> motoNoEncontradaerrores = Map.of(
          "status", mne.getMessage(),
          "error", 404
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(motoNoEncontradaerrores);
    }

    @ExceptionHandler(DatosErroneosException.class)
    public ResponseEntity<Map<String, Object>> datosErronesExcepciones(DatosErroneosException dee){
        Map<String, Object> datosErroneos = Map.of(
          "status", dee.getMessage(),
          "error", 400
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(datosErroneos);
    }
}
