package com.climaapp.backend.controller;

import com.climaapp.backend.model.Busqueda;
import com.climaapp.backend.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clima")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ClimaController {

    @Autowired
    private ClimaService climaService;

    @GetMapping("/buscar-externo/{ciudad}")
    public ResponseEntity<Busqueda> buscarClima(@PathVariable String ciudad) {
        Busqueda resultado = climaService.consultarClimaExterno(ciudad);
        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/historial")
    public ResponseEntity<List<Busqueda>> obtenerHistorial() {
        List<Busqueda> historial = climaService.obtenerHistorial();
        return ResponseEntity.ok(historial);
    }

    @DeleteMapping("/historial")
    public ResponseEntity<Void> borrarHistorial() {
        climaService.borrarHistorial();
        return ResponseEntity.noContent().build();
    }
}
