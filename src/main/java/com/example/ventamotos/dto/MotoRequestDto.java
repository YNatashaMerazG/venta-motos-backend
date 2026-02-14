package com.example.ventamotos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


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
    @NotNull(message = "Ingresa el precio de venta de la motocicleta")
    private BigDecimal precioVenta;
    @NotNull(message = "Ingresa el costo de las reparaciones de la motocicleta")
    private BigDecimal costoReparaciones;
    @NotBlank(message = "Ingresa las placas de la motocicleta")
    private String placa;
    @NotNull(message = "Selecciona si la factura es original")
    private Boolean facturaOriginal;
    @NotNull(message = "Ingresa los adeudos de la motocicleta")
    private BigDecimal adeudos;
    @NotNull(message = "Ingresa el estado estetico del 1 al 10")
    private Integer estetico;
    @NotBlank(message = "Ingresa el color de la motocicleta")
    private String color;
    @NotNull(message = "Ingresa en que estatus se encuentra la motocicleta")
    private Boolean estatus; // false=disponible, true=vendida
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Boolean getFacturaOriginal() {
        return facturaOriginal;
    }

    public void setFacturaOriginal(Boolean facturaOriginal) {
        this.facturaOriginal = facturaOriginal;
    }

    public BigDecimal getAdeudos() {
        return adeudos;
    }

    public void setAdeudos(BigDecimal adeudos) {
        this.adeudos = adeudos;
    }

    public Integer getEstetico() {
        return estetico;
    }

    public void setEstetico(Integer estetico) {
        this.estetico = estetico;
    }
}
