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
    public ResponseEntity<List<MotosModel>> listaMotos(){
        return ResponseEntity.ok(motosService.listaMotos());
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<MotosModel> buscarID(@PathVariable int id){
        MotosModel motoID = motosService.buscarMotoID(id);
        return ResponseEntity.ok(motoID);
    }

    //BUSCAR POR MARCA
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<MotosModel>> buscarMarca(@PathVariable String marca){
        List<MotosModel> motoMarca = motosService.buscarMarca(marca);
        return ResponseEntity.ok(motoMarca);
    }

    //BUSCAR POR CILINDRADA
    @GetMapping("/cilindrada/{cilindrada}")
    public ResponseEntity<List<MotosModel>> buscarCilindrada(@PathVariable Integer cilindrada){
        List<MotosModel> motoCilindrada = motosService.buscarCC(cilindrada);
        return ResponseEntity.ok(motoCilindrada);
    }

    //BUSCAR POR ANIO
    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<MotosModel>> buscarAnio(@PathVariable Integer anio){
        List<MotosModel> motoAnio = motosService.buscarAnio(anio);
        return ResponseEntity.ok(motoAnio);
    }

    //BUSQUEDA POR ESTATUS
    @GetMapping("/estatus/{estatus}")
    public ResponseEntity<List<MotosModel>> busquedaEstatus(@PathVariable Boolean estatus){
        List<MotosModel> motoEstatus = motosService.buscarEstatus(estatus);
        return ResponseEntity.ok(motoEstatus);
    }

    //BUSQUEDA RANGO PRECIO
    @GetMapping("/precio")
    public ResponseEntity<List<MotosModel>> rangoPrecio(@RequestParam BigDecimal min, @RequestParam BigDecimal max){
        List<MotosModel> motorangoprecio = motosService.buscarRangoPrecio(min, max);
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
    public ResponseEntity<MotosModel> motoActualizar(@PathVariable int id, @RequestBody MotosModel nuevaInfo){
        MotosModel actualizarMoto = motosService.actualizarInfo(id, nuevaInfo);
        return ResponseEntity.ok(actualizarMoto);
    }

    //GANANCIA ESTIMADA
    @GetMapping("/{id}/ganancia")
    public ResponseEntity<BigDecimal> ganancia (@PathVariable int id){
        BigDecimal motoGanancia = motosService.gananciaEstimada(id);
        return ResponseEntity.ok(motoGanancia);
    }

    //MARCAR MOTO COMO VENDIDA
    @PutMapping("/{id}/vendida")
    public ResponseEntity<MotosModel> marcarVendida(@PathVariable int id){
        MotosModel motoVendida = motosService.motoVendida(id);
        return ResponseEntity.ok(motoVendida);
    }

    //MARCAR COMO MOTO DISPONIBLE
    @PutMapping("/{id}/disponible")
    public ResponseEntity<MotosModel> marcarDisponible(@PathVariable int id){
        MotosModel motoDisponible = motosService.motoDisponible(id);
        return ResponseEntity.ok(motoDisponible);
    }
}
