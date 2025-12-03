package com.example.backend_cafedronel.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.backend_cafedronel.model.Venta;
import com.example.backend_cafedronel.service.VentaService;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // ============================
    // LISTAR TODAS LAS VENTAS
    // ============================
    @GetMapping
    public List<Venta> getAll() {
        return ventaService.listar();
    }

    // ============================
    // CREAR UNA NUEVA VENTA
    // ============================
    @PostMapping
    public Venta create(@RequestBody Venta venta) {
        return ventaService.registrar(venta);
    }
}
