package com.example.ventamotos.controller;

import com.example.ventamotos.dto.MotoPublicResponseDto;
import com.example.ventamotos.dto.MotoResponseDto;
import com.example.ventamotos.service.MotosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/motos/public")
public class MotoPublicController {

    private final MotosService motosService;

    public MotoPublicController(MotosService motosService) {
        this.motosService = motosService;
    }

    //BUSQUEDA POR DISPONIBLE (USUARIO)
    @GetMapping("/disponibles")
    public ResponseEntity<List<MotoPublicResponseDto>> busquedaPublicDisponible(){
        return ResponseEntity.ok(motosService.buscarPublicDisponibles());
    }
    //BUSCAR POR MARCA (USUARIO)
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<MotoPublicResponseDto>> buscarMarcapublic(@PathVariable String marca){
        return ResponseEntity.ok(motosService.buscarMarcapublic(marca));
    }

    //BUSCAR POR CILINDRADA (USUARIO)
    @GetMapping("/cilindrada/{cilindrada}")
    public ResponseEntity<List<MotoPublicResponseDto>> buscarcilindradapublic(@PathVariable Integer cilindrada){
        List<MotoPublicResponseDto> motoCilindrada = motosService.buscarCCpublic(cilindrada);
        return ResponseEntity.ok(motoCilindrada);
    }
    //BUSCAR POR ANIO (USUARIO)
    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<MotoPublicResponseDto>> buscaraniopublic(@PathVariable Integer anio){
        List<MotoPublicResponseDto> motoaniopublic = motosService.buscaraniopublic(anio);
        return ResponseEntity.ok(motoaniopublic);
    }

    //BUSQUEDA POR ESTATUS (USUARIO)
    @GetMapping("/estatus/{estatus}")
    public ResponseEntity<List<MotoPublicResponseDto>> busquedaestatuspublic(@PathVariable boolean estatus){
        List<MotoPublicResponseDto> motoestatusp = motosService.buscarEstatuspublic(estatus);
        return ResponseEntity.ok(motoestatusp);
    }

    //BUSQUEDA RANGO PRECIO (USUARIO)
    @GetMapping("/precio")
    public ResponseEntity<List<MotoPublicResponseDto>> rangopreciopublic(@RequestParam BigDecimal min, @RequestParam BigDecimal max){
        List<MotoPublicResponseDto> motorangopublic = motosService.buscarrangopreciopublic(min,max);
        return ResponseEntity.ok(motorangopublic);
    }
}
