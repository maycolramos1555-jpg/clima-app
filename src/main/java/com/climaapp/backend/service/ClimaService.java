package com.climaapp.backend.service;

import com.climaapp.backend.model.Busqueda;
import com.climaapp.backend.repository.BusquedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class ClimaService {

    @Autowired
    private BusquedaRepository busquedaRepository;

    private static final Random random = new Random();
    private static final String[] PAISES = {"ES", "MX", "AR", "CO", "CL", "PE", "VE"};
    private static final String[] DESCRIPCIONES = {
        "Soleado", "Nublado", "Lluvia", "Parcialmente nublado",
        "Despejado", "Lluvias ligeras", "Muy nublado", "Tormentas"
    };

    public Busqueda guardarBusqueda(Busqueda busqueda) {
        // Establecer fecha actual si no viene
        if (busqueda.getFechaBusqueda() == null) {
            busqueda.setFechaBusqueda(LocalDateTime.now());
        }
        return busquedaRepository.save(busqueda);
    }

    public List<Busqueda> obtenerHistorial() {
        return busquedaRepository.findAll();
    }

    public List<Busqueda> buscarPorCiudad(String ciudad) {
        return busquedaRepository.findByCiudadIgnoreCase(ciudad);
    }

    // Generar datos de clima simulado
    public Busqueda generarClimaSimulado(String ciudad) {
        Busqueda busqueda = new Busqueda();
        busqueda.setCiudad(ciudad.substring(0, 1).toUpperCase() + ciudad.substring(1).toLowerCase());
        busqueda.setPais(PAISES[random.nextInt(PAISES.length)]);
        busqueda.setTemperatura(Double.valueOf(random.nextInt(35) + 5)); // Entre 5°C y 40°C
        busqueda.setDescripcion(DESCRIPCIONES[random.nextInt(DESCRIPCIONES.length)]);
        busqueda.setFechaBusqueda(LocalDateTime.now());

        return guardarBusqueda(busqueda);
    }
}