package com.climaapp.backend.controller;

import com.climaapp.backend.model.Busqueda;
import com.climaapp.backend.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clima")
@CrossOrigin(origins = "*")
public class ClimaController {

    @Autowired
    private ClimaService climaService;

    @GetMapping("/historial")
    public List<Busqueda> obtenerHistorial() {
        return climaService.obtenerHistorial();
    }

    @PostMapping("/guardar")
    public Busqueda guardarBusqueda(@RequestBody Busqueda busqueda) {
        return climaService.guardarBusqueda(busqueda);
    }

    @GetMapping("/buscar/{ciudad}")
    public List<Busqueda> buscarPorCiudad(@PathVariable String ciudad) {
        return climaService.buscarPorCiudad(ciudad);
    }
}