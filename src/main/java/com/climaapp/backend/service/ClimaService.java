package com.climaapp.backend.service;

import com.climaapp.backend.model.Busqueda;
import com.climaapp.backend.repository.BusquedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClimaService {

    @Autowired
    private BusquedaRepository busquedaRepository;

    public Busqueda guardarBusqueda(Busqueda busqueda) {
        return busquedaRepository.save(busqueda);
    }

    public List<Busqueda> obtenerHistorial() {
        return busquedaRepository.findAll();
    }

    public List<Busqueda> buscarPorCiudad(String ciudad) {
        return busquedaRepository.findByCiudadIgnoreCase(ciudad);
    }
}