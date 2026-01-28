package com.example.ventamotos.controller;

import com.example.ventamotos.dto.MotoRequestDto;
import com.example.ventamotos.dto.MotoResponseDto;
import com.example.ventamotos.model.MotosModel;
import com.example.ventamotos.service.MotosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotosController {

    private final MotosService motosService;

    public MotosController(MotosService motosService){
        this.motosService = motosService;
    }

    //INSERTAR MOTO
    @PostMapping
    public ResponseEntity<MotoResponseDto> insertarMoto(@Valid @RequestBody MotoRequestDto moto){
        MotoResponseDto response = motosService.insertarMoto(moto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //LISTA DE MOTOS
    @GetMapping
    public ResponseEntity<List<MotoResponseDto>> listaMotos(){
        return ResponseEntity.ok(motosService.listaMotos());
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<MotoResponseDto> buscarID(@PathVariable int id){
        return ResponseEntity.ok(motosService.buscarmotoporID(id));
    }

    //BUSCAR POR MARCA
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<MotoResponseDto>> buscarMarca(@PathVariable String marca){
        return ResponseEntity.ok(motosService.buscarMarca(marca));
    }

    //BUSCAR POR CILINDRADA
    @GetMapping("/cilindrada/{cilindrada}")
    public ResponseEntity<List<MotoResponseDto>> buscarCilindrada(@PathVariable Integer cilindrada){
        List<MotoResponseDto> motoCilindrada = motosService.buscarCC(cilindrada);
        return ResponseEntity.ok(motoCilindrada);
    }

    //BUSCAR POR ANIO
    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<MotoResponseDto>> buscarAnio(@PathVariable Integer anio){
        List<MotoResponseDto> motoAnio = motosService.buscarAnio(anio);
        return ResponseEntity.ok(motoAnio);
    }

    //BUSQUEDA POR ESTATUS
    @GetMapping("/estatus/{estatus}")
    public ResponseEntity<List<MotoResponseDto>> busquedaEstatus(@PathVariable Boolean estatus){
        List<MotoResponseDto> motoEstatus = motosService.buscarEstatus(estatus);
        return ResponseEntity.ok(motoEstatus);
    }

    //BUSQUEDA RANGO PRECIO
    @GetMapping("/precio")
    public ResponseEntity<List<MotoResponseDto>> rangoPrecio(@RequestParam BigDecimal min, @RequestParam BigDecimal max){
        List<MotoResponseDto> motorangoprecio = motosService.buscarRangoPrecio(min, max);
        return ResponseEntity.ok(motorangoprecio);
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMoto (@PathVariable int id){
        motosService.eliminarMoto(id);

        return ResponseEntity.noContent().build();
    }

    //ACTUALIZAR INFORMACION
    @PatchMapping("/{id}")
    public ResponseEntity<MotoResponseDto> motoActualizar(@PathVariable int id, @RequestBody MotoRequestDto nuevaInfo){
        return ResponseEntity.ok(motosService.actualizarInfo(id, nuevaInfo));
    }

    //GANANCIA ESTIMADA
    @GetMapping("/{id}/ganancia")
    public ResponseEntity<BigDecimal> ganancia (@PathVariable int id){
        BigDecimal motoGanancia = motosService.gananciaEstimada(id);
        return ResponseEntity.ok(motoGanancia != null ? motoGanancia : BigDecimal.ZERO);    }

    //MARCAR MOTO COMO VENDIDA
    @PutMapping("/{id}/vendida")
    public ResponseEntity<MotoResponseDto> marcarVendida(@PathVariable int id){
       return ResponseEntity.ok(motosService.motoVendida(id));
    }

    //MARCAR COMO MOTO DISPONIBLE
    @PutMapping("/{id}/disponible")
    public ResponseEntity<MotoResponseDto> marcarDisponible(@PathVariable int id){
            return ResponseEntity.ok(motosService.motoDisponible(id));
    }
}
