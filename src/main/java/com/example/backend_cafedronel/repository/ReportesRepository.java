package com.example.backend_cafedronel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.backend_cafedronel.model.VentaItem;

@Repository
public interface ReportesRepository extends JpaRepository<VentaItem, Integer> {

    @Query(
        "SELECT FUNCTION('MONTH', i.venta.fechaVenta), SUM(i.cantidad * i.precioUnitario) " +
        "FROM VentaItem i " +
        "GROUP BY FUNCTION('MONTH', i.venta.fechaVenta) " +
        "ORDER BY FUNCTION('MONTH', i.venta.fechaVenta)"
    )
    List<Object[]> ventasPorMes();

    @Query(
        "SELECT i.producto, SUM(i.cantidad) " +
        "FROM VentaItem i " +
        "GROUP BY i.producto"
    )
    List<Object[]> ventasPorCategoria();

    @Query(
        "SELECT i.producto, SUM(i.cantidad) " +
        "FROM VentaItem i " +
        "GROUP BY i.producto " +
        "ORDER BY SUM(i.cantidad) DESC"
    )
    List<Object[]> topProductos();
}
