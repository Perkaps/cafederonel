package com.example.backend_cafedronel.model;

import com.example.backend_cafedronel.repository.ReportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Reportes {

    @Autowired
    private ReportesRepository reportesRepository;

    public List<Map<String, Object>> getVentasPorMes() {
        List<Object[]> results = reportesRepository.ventasPorMes();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("mes", row[0]);
            map.put("total", row[1]);
            response.add(map);
        }
        return response;
    }

    public List<Map<String, Object>> getVentasPorCategoria() {
        List<Object[]> results = reportesRepository.ventasPorCategoria();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("producto", row[0]);
            map.put("cantidad", row[1]);
            response.add(map);
        }
        return response;
    }

    public List<Map<String, Object>> getTopProductos() {
        List<Object[]> results = reportesRepository.topProductos();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("producto", row[0]);
            map.put("cantidad", row[1]);
            response.add(map);
        }
        return response;
    }
}
