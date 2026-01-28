package com.example.ventamotos.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Formatter;

public class MotoRequestDto {

    @NotBlank(message = "Ingresa la marca de la motocicleta")
    private String marca;
    @NotBlank(message = "Ingresa el modelo de la motocicleta")
    private String modelo;
    @NotNull(message = "Ingresa la cilimdrada de la motocicleta")
    private Integer cilindrada;
    @NotNull(message = "Ingresa el año de la motocicleta")
    private Integer anio;
    @NotNull(message = "Ingresa el kilometraje de la motocicleta")
    private Long kilometraje;
    @Column(precision = 10, scale = 2)
    @NotNull(message = "Ingresa el precio de venta de la motocicleta")
    private BigDecimal precioVenta;
    @NotNull(message = "Ingresa el costo de las reparaciones de la motocicleta")
    @Column(precision = 10, scale = 2)
    private BigDecimal costoReparaciones;
    @NotBlank(message = "Ingresa el color de la motocicleta")
    private String color;
    @NotNull(message = "Ingresa en que estatus se encuentra la motocicleta")
    private Boolean estatus;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Long getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Long kilometraje) {
        this.kilometraje = kilometraje;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getCostoReparaciones() {
        return costoReparaciones;
    }

    public void setCostoReparaciones(BigDecimal costoReparaciones) {
        this.costoReparaciones = costoReparaciones;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
}
