package com.example.ventamotos.service;

import com.example.ventamotos.excepciones.DatosErroneosException;
import com.example.ventamotos.excepciones.MotoNoEncontradaException;
import com.example.ventamotos.model.MotosModel;
import com.example.ventamotos.repository.MotosRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MotosService {

    private final MotosRepository motosRepository;

    public MotosService(MotosRepository motosRepository){
        this.motosRepository = motosRepository;
    }

    //VALIDAR DATOS
    public void validarDatos(MotosModel moto){
        if(moto.getPrecioVenta() !=null && moto.getPrecioVenta().compareTo(BigDecimal.ZERO) < 0){
            throw new DatosErroneosException("El precio no puede ser negativo");
        }
        if(moto.getAnio() !=null && moto.getAnio() > java.time.Year.now().getValue()){
            throw new DatosErroneosException("El año no puede ser mayor al actual");
        }
        if(moto.getCilindrada() != null && moto.getCilindrada() <= 0){
            throw new DatosErroneosException("La cilindrada no puede ser negativa");
        }
    }

    //INSERTAR MOTO
    public MotosModel insertarMoto(MotosModel motosModel){
        validarDatos(motosModel);
        return motosRepository.save(motosModel);
    }

    //LISTA DE LAS MOTOS
    public List<MotosModel> listaMotos(){
        return motosRepository.findAll();
    }

    //BUSCAR POR ID
    public MotosModel buscarMotoID(int id){
        return motosRepository.findById(id)
                .orElseThrow(() -> new MotoNoEncontradaException("No se encontraron resultados"));
    }

    //BUSCAR POR MARCA
    public List<MotosModel> buscarMarca(String marca){
        return motosRepository.findByMarcaContainingIgnoreCase(marca);
    }

    //BUSCAR POR CILINDRADA
    public List<MotosModel> buscarCC(Integer cilindrada){
        return motosRepository.findByCilindrada(cilindrada);
    }

    //BUSCAR POR ANIO
    public List<MotosModel> buscarAnio(Integer anio){
        return motosRepository.findByAnio(anio);
    }

    //BUSQUEDA POR STATUS
    public List<MotosModel> buscarEstatus(Boolean estatus){
        return motosRepository.findByEstatus(estatus);
    }

    //BUSQUEDA POR RANGO DE PRECIO
    public List<MotosModel> buscarRangoPrecio(BigDecimal min, BigDecimal max){
        return motosRepository.findByPrecioVentaBetween(min, max);
    }

    //ELIMINAR
    public void eliminarMoto(int id){
        MotosModel moto = buscarMotoID(id);

        if(Boolean.FALSE.equals(moto.getEstatus())){
            throw new IllegalStateException("No puedes eliminar una moto que ya ha sido vendida");
        }

        motosRepository.deleteById(id);
    }

    //ACTUALIZAR INFORMACION (Patch update: actualizacion parcial)
    public MotosModel actualizarInfo(int id, MotosModel infonueva){
        MotosModel moto = buscarMotoID(id);
        if(infonueva.getMarca() != null) moto.setMarca(infonueva.getMarca());
        if(infonueva.getModelo() != null) moto.setModelo(infonueva.getModelo());
        if(infonueva.getCilindrada() != null) moto.setCilindrada(infonueva.getCilindrada());
        if(infonueva.getAnio() != null) moto.setAnio(infonueva.getAnio());
        if(infonueva.getKilometraje() != null) moto.setKilometraje(infonueva.getKilometraje());
        if(infonueva.getPrecioVenta() != null) moto.setPrecioVenta(infonueva.getPrecioVenta());
        if(infonueva.getCostoReparaciones() != null) moto.setCostoReparaciones(infonueva.getCostoReparaciones());
        if(infonueva.getPlaca() != null) moto.setPlaca(infonueva.getPlaca());
        if(infonueva.getFacturaOriginal() != null) moto.setFacturaOriginal(infonueva.getFacturaOriginal());
        if(infonueva.getAdeudos() != null) moto.setAdeudos(infonueva.getAdeudos());
        if(infonueva.getEstetico() != null) moto.setEstetico(infonueva.getEstetico());
        if(infonueva.getColor() != null) moto.setColor(infonueva.getColor());
        if(infonueva.getEstatus() != null) moto.setEstatus(infonueva.getEstatus());

        validarDatos(moto);
        return motosRepository.save(moto);
    }

    //GANANCIA ESTIMADA
    public BigDecimal gananciaEstimada(int id){
        MotosModel moto = buscarMotoID(id);

        BigDecimal precioVenta = (moto.getPrecioVenta() != null) ? moto.getPrecioVenta() : BigDecimal.ZERO;
        BigDecimal costoReparacion = (moto.getCostoReparaciones() != null) ? moto.getCostoReparaciones() : BigDecimal.ZERO;

        return precioVenta.subtract(costoReparacion);
    }

    //MARCAR MOTO COMO VENDIDA
    public MotosModel motoVendida(int id){
        MotosModel motovendida = buscarMotoID(id);

        motovendida.setEstatus(true);
        return motosRepository.save(motovendida);
    }

    //MARCAR MOTO COMO DISPONIBLE
    public MotosModel motoDisponible(int id){
        MotosModel motoDisponible = buscarMotoID(id);

        motoDisponible.setEstatus(false);
        return motosRepository.save(motoDisponible);
    }

}
