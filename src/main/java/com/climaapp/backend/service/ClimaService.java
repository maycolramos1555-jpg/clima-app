package com.climaapp.backend.service;

import com.climaapp.backend.model.Busqueda;
import com.climaapp.backend.repository.BusquedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ClimaService {

    @Autowired
    private BusquedaRepository busquedaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q={ciudad}&appid={apiKey}&units=metric&lang=es";

    public Busqueda guardarBusqueda(Busqueda busqueda) {
        if (busqueda.getFechaBusqueda() == null) {
            busqueda.setFechaBusqueda(LocalDateTime.now());
        }
        return busquedaRepository.save(busqueda);
    }

    public List<Busqueda> obtenerHistorial() {
        return busquedaRepository.findAll();
    }

    public void borrarHistorial() {
        busquedaRepository.deleteAll();
    }

    public Busqueda consultarClimaExterno(String ciudad) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(API_URL, Map.class, ciudad, apiKey);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> body = response.getBody();
                
                Busqueda busqueda = new Busqueda();
                busqueda.setCiudad((String) body.get("name"));
                
                Map<String, Object> sys = (Map<String, Object>) body.get("sys");
                busqueda.setPais((String) sys.get("country"));
                
                Map<String, Object> main = (Map<String, Object>) body.get("main");
                busqueda.setTemperatura(Double.valueOf(main.get("temp").toString()));
                
                List<Map<String, Object>> weather = (List<Map<String, Object>>) body.get("weather");
                busqueda.setDescripcion((String) weather.get(0).get("description"));
                
                busqueda.setFechaBusqueda(LocalDateTime.now());
                
                return guardarBusqueda(busqueda);
            }
        } catch (Exception e) {
            System.err.println("Error al consultar API externa: " + e.getMessage());
        }
        return null;
    }
}
