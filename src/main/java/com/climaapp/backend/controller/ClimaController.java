package com.climaapp.backend.controller;

import com.climaapp.backend.model.Busqueda;
import com.climaapp.backend.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clima")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClimaController {

    @Autowired
    private ClimaService climaService;

    @GetMapping("/historial")
    public ResponseEntity<List<Busqueda>> obtenerHistorial() {
        try {
            List<Busqueda> historial = climaService.obtenerHistorial();
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Busqueda> guardarBusqueda(@RequestBody Busqueda busqueda) {
        try {
            if (busqueda.getCiudad() == null || busqueda.getCiudad().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Busqueda guardada = climaService.guardarBusqueda(busqueda);
            return ResponseEntity.ok(guardada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscar/{ciudad}")
    public ResponseEntity<List<Busqueda>> buscarPorCiudad(@PathVariable String ciudad) {
        try {
            if (ciudad == null || ciudad.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            List<Busqueda> resultados = climaService.buscarPorCiudad(ciudad);
            return ResponseEntity.ok(resultados);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/simular/{ciudad}")
    public ResponseEntity<Busqueda> generarClimaSimulado(@PathVariable String ciudad) {
        try {
            if (ciudad == null || ciudad.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Busqueda generada = climaService.generarClimaSimulado(ciudad);
            return ResponseEntity.ok(generada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}