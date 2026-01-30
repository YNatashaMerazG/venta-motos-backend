package com.example.ventamotos;

import com.example.ventamotos.dto.MotoRequestDto;
import com.example.ventamotos.dto.MotoResponseDto;
import com.example.ventamotos.model.MotosModel;
import com.example.ventamotos.repository.MotosRepository;
import com.example.ventamotos.service.MotosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MotoServiceTest {

    @Mock
    private MotosRepository motosRepository;

    @InjectMocks
    private MotosService motosService;

    @Test
    void insertarMoto_Guarda_Retorna(){
        //Guardamos
        MotoRequestDto ingresar = new MotoRequestDto();
        ingresar.setMarca("Yamaha");
        ingresar.setModelo("Mt 15");
        ingresar.setCilindrada(125);
        ingresar.setAnio(2024);
        ingresar.setKilometraje(7084L);
        ingresar.setPrecioVenta(new BigDecimal(99064.90));
        ingresar.setCostoReparaciones(new BigDecimal(200));
        ingresar.setPlaca("2875MPZ");
        ingresar.setFacturaOriginal(true);
        ingresar.setAdeudos(new BigDecimal(2000));
        ingresar.setEstetico(10);
        ingresar.setColor("Azul");
        ingresar.setEstatus(false);

        //Simulamos
        MotosModel guardado = new MotosModel();
        guardado.setId(1);
        guardado.setMarca("Yamaha");
        guardado.setModelo("Mt 15");
        guardado.setCilindrada(125);
        guardado.setAnio(2024);
        guardado.setKilometraje(7084L);
        guardado.setPrecioVenta(new BigDecimal(99064.90));
        guardado.setColor("Azul");

        when(motosRepository.save(any(MotosModel.class)))
                .thenReturn(guardado);

        MotoResponseDto response = motosService.insertarMoto(ingresar);

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("Yamaha", response.getMarca());
        assertEquals("Mt 15", response.getModelo());
    }

    @Test
    void eliminarMoto(){
        MotosModel eliminar = new MotosModel();
        eliminar.setId(7);
        eliminar.setEstatus(true);

        when(motosRepository.findById(7))
                .thenReturn(Optional.of(eliminar));

        motosService.eliminarMoto(7);
        verify(motosRepository).deleteById(7);
    }

    @Test
    void eliminarMoto_Vendida_Error(){

        MotosModel moto = new MotosModel();
        moto.setId(3);
        moto.setEstatus(false);

        when(motosRepository.findById(3))
                .thenReturn(Optional.of(moto));

        assertThrows(IllegalStateException.class, () -> {
            motosService.eliminarMoto(3);
        });

        verify(motosRepository, never()).deleteById(anyInt());
    }

}
