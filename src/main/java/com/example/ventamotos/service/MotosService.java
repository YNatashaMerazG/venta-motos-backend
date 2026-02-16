package com.example.ventamotos.service;

import com.example.ventamotos.dto.MotoPublicResponseDto;
import com.example.ventamotos.dto.MotoResponseDto;
import com.example.ventamotos.excepciones.DatosErroneosException;
import com.example.ventamotos.excepciones.MotoNoEncontradaException;
import com.example.ventamotos.model.MotosModel;
import com.example.ventamotos.dto.MotoRequestDto;
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

    // =========================
    // ADMIN
    // =========================

    //INSERTAR MOTO
    public MotoResponseDto insertarMoto(MotoRequestDto dto){
        MotosModel moto = mapToEntity(dto); //usuario
        validarDatos(moto);
        MotosModel motoGuardada = motosRepository.save(moto);
        return mapToResponse(motoGuardada);
    }

    //LISTA DE LAS MOTOS (admin)
    public List<MotoResponseDto> listaMotos(){
        return mapToResponseList(motosRepository.findAll());
    }

    private List<MotoResponseDto> mapToResponseList(List<MotosModel> motos) {
        return motos.stream()
                .map(this::mapToResponse)
                .toList();
    }

    //BUSCAR POR ID (ENTITY)
    public MotosModel buscarMotoEntity(int id){
        return motosRepository.findById(id)
                .orElseThrow(() -> new MotoNoEncontradaException("No se encontraron resultados"));
    }

    //BUSCAR POR ID
    public MotoResponseDto buscarmotoporID(int id){
        MotosModel moto = buscarMotoEntity(id);
        return mapToResponse(moto);
    }

    //BUSCAR POR MARCA (ADMIN)
    public List<MotoResponseDto> buscarMarca(String marca){
        List<MotosModel> moto = motosRepository.findByMarcaContainingIgnoreCase(marca);
        return moto.stream()
                .map(this::mapToResponse)
                .toList();
    }

    //BUSCAR POR CILINDRADA (ADMIN)
    public List<MotoResponseDto> buscarCC(Integer cilindrada){
        List<MotosModel> motocc = motosRepository.findByCilindrada(cilindrada);
        return motocc.stream()
                .map(this::mapToResponse)
                .toList();
    }
    //BUSQUEDA POR STATUS (ADMIN)
    public List<MotoResponseDto> buscarEstatus(Boolean estatus){
        List<MotosModel> motoestatus = motosRepository.findByEstatus(estatus);
        return motoestatus.stream()
                .map(this::mapToResponse)
                .toList();
    }
    //BUSCAR POR ANIO (ADMIN)
    public List<MotoResponseDto> buscarAnio(Integer anio){
        List<MotosModel> motoanio = motosRepository.findByAnio(anio);
        return motoanio.stream()
                .map(this::mapToResponse)
                .toList();
    }
    //BUSQUEDA POR RANGO DE PRECIO (ADMIN)
    public List<MotoResponseDto> buscarRangoPrecio(BigDecimal min, BigDecimal max){
        List<MotosModel> motos = motosRepository.findByPrecioVentaBetween(min, max);
        return motos.stream()
                .map(this::mapToResponse)
                .toList();
    }

    //ELIMINAR
    public void eliminarMoto(int id){
        MotosModel moto = buscarMotoEntity(id);

        if(Boolean.FALSE.equals(moto.getEstatus())){
            throw new IllegalStateException("No puedes eliminar una moto que ya ha sido vendida");
        }

        motosRepository.deleteById(id);
    }

    //ACTUALIZAR INFORMACION (Patch update: actualizacion parcial)
    public MotoResponseDto actualizarInfo(int id, MotoRequestDto infonueva){
        MotosModel moto = buscarMotoEntity(id);
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
        return mapToResponse(motosRepository.save(moto));
    }

    //GANANCIA ESTIMADA
    public BigDecimal gananciaEstimada(int id){
        MotosModel moto = buscarMotoEntity(id);

        BigDecimal precioVenta = (moto.getPrecioVenta() != null) ? moto.getPrecioVenta() : BigDecimal.ZERO;
        BigDecimal costoReparacion = (moto.getCostoReparaciones() != null) ? moto.getCostoReparaciones() : BigDecimal.ZERO;
        BigDecimal adeudos = (moto.getAdeudos() != null) ? moto.getAdeudos() : BigDecimal.ZERO;

        return precioVenta.subtract(costoReparacion).subtract(adeudos);
    }

    //MARCAR MOTO COMO VENDIDA
    public MotoResponseDto motoVendida(int id){
        MotosModel motovendida = buscarMotoEntity(id);

        motovendida.setEstatus(true);
        return mapToResponse(motosRepository.save(motovendida));
    }

    //MARCAR MOTO COMO DISPONIBLE
    public MotoResponseDto motoDisponible(int id){
        MotosModel motoDisponible = buscarMotoEntity(id);

        motoDisponible.setEstatus(false);
        return mapToResponse(motosRepository.save(motoDisponible));
    }

    // =========================
    // USUARIO
    // =========================

    //SOLO DISPONIBLES (usuario)
    public List<MotoPublicResponseDto> buscarPublicDisponibles(){
        List<MotosModel> motodisponible = motosRepository.findByEstatusFalse();
        return motodisponible.stream()
                .map(this::mapPublicResponseDto)
                .toList();
    }

    //BUSCAR POR MARCA (USUARIO)
    public List<MotoPublicResponseDto> buscarMarcapublic(String marca){
        List<MotosModel> motos = motosRepository.findByMarcaContainingIgnoreCase(marca);
        return motos.stream()
                .map(this::mapPublicResponseDto)
                .toList();
    }

    //BUSAR POR CILINDRADA (USUARIO)
    public List<MotoPublicResponseDto> buscarCCpublic(Integer cilindrada){
        List<MotosModel> motocc = motosRepository.findByCilindrada(cilindrada);
        return  motocc.stream()
                .map(this::mapPublicResponseDto)
                .toList();
    }

    //BUSCAR POR ANIO (USUARIO)
    public List<MotoPublicResponseDto> buscaraniopublic(Integer anio){
        List<MotosModel> motoaniop = motosRepository.findByAnio(anio);
        return motoaniop.stream()
                .map(this::mapPublicResponseDto)
                .toList();
    }

    //BUSQUEDA POR RANGO DE PRECIO (USUARIO)
    public List<MotoPublicResponseDto> buscarrangopreciopublic (BigDecimal min, BigDecimal max){
        List<MotosModel> motos = motosRepository.findByPrecioVentaBetween(min, max);
        return motos.stream()
                .map(this::mapPublicResponseDto)
                .toList();
    }

    private MotosModel mapToEntity(MotoRequestDto dto) {
        MotosModel moto = new MotosModel();
        moto.setMarca(dto.getMarca());
        moto.setModelo(dto.getModelo());
        moto.setCilindrada(dto.getCilindrada());
        moto.setAnio(dto.getAnio());
        moto.setKilometraje(dto.getKilometraje());
        moto.setPrecioVenta(dto.getPrecioVenta());
        moto.setCostoReparaciones(dto.getCostoReparaciones());
        moto.setPlaca(dto.getPlaca());
        moto.setFacturaOriginal(dto.getFacturaOriginal());
        moto.setAdeudos(dto.getAdeudos());
        moto.setEstetico(dto.getEstetico());
        moto.setColor(dto.getColor());
        moto.setEstatus(dto.getEstatus() != null ? dto.getEstatus() : false);
        return moto;
    }

    private MotoResponseDto mapToResponse(MotosModel moto) {
        MotoResponseDto dto = new MotoResponseDto();
        dto.setId(moto.getId());
        dto.setMarca(moto.getMarca());
        dto.setModelo(moto.getModelo());
        dto.setCilindrada(moto.getCilindrada());
        dto.setAnio(moto.getAnio());
        dto.setKilometraje(moto.getKilometraje());
        dto.setColor(moto.getColor());
        dto.setPlaca(moto.getPlaca());
        dto.setEstetico(moto.getEstetico());
        dto.setPrecioVenta(moto.getPrecioVenta());
        dto.setCostoReparaciones(moto.getCostoReparaciones());
        dto.setAdeudos(moto.getAdeudos());
        dto.setEstatus(moto.getEstatus());
        return dto;
    }

    private MotoPublicResponseDto mapPublicResponseDto(MotosModel moto){
        MotoPublicResponseDto pdto = new MotoPublicResponseDto();
        pdto.setMarca(moto.getMarca());
        pdto.setModelo(moto.getModelo());
        pdto.setCilindrada(moto.getCilindrada());
        pdto.setAnio(moto.getAnio());
        pdto.setKilometraje(moto.getKilometraje());
        pdto.setColor(moto.getColor());
        pdto.setPrecioVenta(moto.getPrecioVenta());
        return pdto;
    }
}
