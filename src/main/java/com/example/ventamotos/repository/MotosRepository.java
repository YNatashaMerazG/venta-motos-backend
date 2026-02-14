package com.example.ventamotos.repository;

import com.example.ventamotos.model.MotosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface MotosRepository extends JpaRepository<MotosModel, Integer> {

    //ENCONTRAR POR MARCA
    List<MotosModel> findByMarcaContainingIgnoreCase(String marca);
    //ENCONTRAR POR CC
    List<MotosModel> findByCilindrada(Integer cilindrada);
    //ENCONTRAR POR ANIO
    List<MotosModel> findByAnio(Integer anio);
    //ENCONTRAR POR STATUS'
    List<MotosModel> findByEstatus(Boolean estatus);
    //ENCONTRAR POR DISPONIBLE
    List<MotosModel> findByEstatusFalse();
    //ENCONTRAR POR UN RANGO DE PRECIO
    List<MotosModel> findByPrecioVentaBetween(BigDecimal min, BigDecimal max);
}
