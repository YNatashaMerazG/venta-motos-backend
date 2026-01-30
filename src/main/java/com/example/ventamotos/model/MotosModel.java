package com.example.ventamotos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "motos")
public class MotosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    @NotBlank(message = "Ingresa las placas de la motocicleta")
    private String placa;
    @NotNull(message = "Selecciona si la factura es original")
    private Boolean facturaOriginal;
    @Column(precision = 8, scale = 2)
    @NotNull(message = "Ingresa los adeudos de la motocicleta")
    private BigDecimal adeudos;
    @NotNull(message = "Ingresa el estado estetico del 1 al 10")
    private Integer estetico;
    @NotBlank(message = "Ingresa el color de la motocicleta")
    private String color;
    @NotNull(message = "Ingresa en que estatus se encuentra la motocicleta")
    private Boolean estatus;

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

