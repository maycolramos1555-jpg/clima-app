package com.climaapp.backend.repository;

import com.climaapp.backend.model.Busqueda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BusquedaRepository extends JpaRepository<Busqueda, Long> {
    List<Busqueda> findByCiudadIgnoreCase(String ciudad);
}