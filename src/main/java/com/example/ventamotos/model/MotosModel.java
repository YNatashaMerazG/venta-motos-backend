package com.example.ventamotos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "motos")
public class MotosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String marca;
    private String modelo;
    private Integer cilindrada;
    private Integer anio;
    private Long kilometraje;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;
    @Column(precision = 10, scale = 2)
    private BigDecimal costoReparaciones;
    private String placa;
    private Boolean facturaOriginal;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal adeudos;
    private Integer estetico;
    private String color;
    private Boolean estatus; // false=disponible, true=vendida

    public MotosModel(){}

    public Integer getId() {
        return id;
    }
    public String getMarca(){
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public Integer getCilindrada() {
        return cilindrada;
    }
    public Integer getAnio() {
        return anio;
    }
    public Long getKilometraje() {
        return kilometraje;
    }
    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }
    public BigDecimal getCostoReparaciones() {
        return costoReparaciones;
    }
    public String getPlaca() {
        return placa;
    }
    public Boolean getFacturaOriginal() {
        return facturaOriginal;
    }
    public BigDecimal getAdeudos() {
        return adeudos;
    }
    public Integer getEstetico() {
        return estetico;
    }
    public String getColor() {
        return color;
    }
    public Boolean getEstatus() {
        return estatus;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setCilindrada(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    public void setKilometraje(Long kilometraje) {
        this.kilometraje = kilometraje;
    }
    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
    public void setCostoReparaciones(BigDecimal costoReparaciones) {
        this.costoReparaciones = costoReparaciones;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setFacturaOriginal(Boolean facturaOriginal) {
        this.facturaOriginal = facturaOriginal;
    }
    public void setAdeudos(BigDecimal adeudos) {
        this.adeudos = adeudos;
    }
    public void setEstetico(Integer estetico) {
        this.estetico = estetico;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

