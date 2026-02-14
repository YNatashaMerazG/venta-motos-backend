package com.example.ventamotos.dto;

import java.math.BigDecimal;

public class MotoPublicResponseDto {
    private String marca;
    private String modelo;
    private Integer cilindrada;
    private Integer anio;
    private Long kilometraje;
    private BigDecimal precioVenta;
    private String color;
    private Boolean facturaOriginal;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getFacturaOriginal() {
        return facturaOriginal;
    }

    public void setFacturaOriginal(Boolean facturaOriginal) {
        this.facturaOriginal = facturaOriginal;
    }
}
