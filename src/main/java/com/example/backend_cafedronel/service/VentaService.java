package com.example.backend_cafedronel.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import com.example.backend_cafedronel.model.Venta;
import com.example.backend_cafedronel.model.VentaItem;
import com.example.backend_cafedronel.repository.VentaRepository;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    public Venta registrar(Venta venta) {

        // Fecha automática
        venta.setFechaVenta(LocalDateTime.now());

        // Calcular total automáticamente si no viene del frontend
        double totalCalculado = 0.0;

        if (venta.getItems() != null && !venta.getItems().isEmpty()) {

            for (VentaItem item : venta.getItems()) {

                // Vincular item → venta
                item.setVenta(venta);

                // Calcular subtotal
                double subtotal = item.getPrecioUnitario() * item.getCantidad();
                totalCalculado += subtotal;
            }
        }

        // Si el frontend no envió total, lo calculo
        if (venta.getTotal() == null || venta.getTotal() == 0) {
            venta.setTotal(totalCalculado);
        }

        return ventaRepository.save(venta);
    }
}
