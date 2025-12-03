package com.example.backend_cafedronel.controller;

import com.example.backend_cafedronel.model.Producto;
import com.example.backend_cafedronel.repository.ProductoRepository;
import com.example.backend_cafedronel.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoService productoService;

    // ===============================
    // LISTAR TODOS
    // ===============================
    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // ===============================
    // OBTENER POR ID
    // ===============================
    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // ===============================
    // CREAR PRODUCTO
    // ===============================
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Producto crearProducto(
            @RequestParam String nombre,
            @RequestParam Double precio,
            @RequestParam String categoria,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) String tamano,
            @RequestParam Integer cantidad,
            @RequestParam(required = false) Boolean destacado,
            @RequestParam(required = false) Boolean activo,
            @RequestParam(required = false) Integer stockMinimo,
            @RequestParam(required = false) Integer stockMaximo,
            @RequestParam(required = false) Integer stockCritico,
            @RequestParam(required = false) MultipartFile imagen
    ) throws IOException {

        Producto p = new Producto();

        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCategoria(categoria);
        p.setDescripcion(descripcion);
        p.setTamano(tamano);
        p.setCantidad(cantidad);

        p.setDestacado(destacado != null ? destacado : false);
        p.setActivo(activo != null ? activo : true);

        p.setStockMinimo(stockMinimo != null ? stockMinimo : 15);
        p.setStockMaximo(stockMaximo != null ? stockMaximo : 2000);
        p.setStockCritico(stockCritico != null ? stockCritico : 1);

        return productoService.crear(p, imagen);
    }

    // ===============================
    // ACTUALIZAR PRODUCTO
    // ===============================
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Producto actualizarProducto(
            @PathVariable Integer id,

            @RequestParam String nombre,
            @RequestParam Double precio,
            @RequestParam String categoria,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) String tamano,
            @RequestParam(required = false) Integer cantidad,
            @RequestParam(required = false) Boolean destacado,
            @RequestParam(required = false) Boolean activo,
            @RequestParam(required = false) Integer stockMinimo,
            @RequestParam(required = false) Integer stockMaximo,
            @RequestParam(required = false) Integer stockCritico,
            @RequestParam(required = false) MultipartFile imagen
    ) throws IOException {

        Producto datos = new Producto();

        datos.setNombre(nombre);
        datos.setDescripcion(descripcion);
        datos.setTamano(tamano);
        datos.setPrecio(precio);
        datos.setCategoria(categoria);
        datos.setCantidad(cantidad);

        datos.setDestacado(destacado);
        datos.setActivo(activo);
        datos.setStockMinimo(stockMinimo);
        datos.setStockMaximo(stockMaximo);
        datos.setStockCritico(stockCritico);

        return productoService.actualizar(id, datos, imagen);
    }

    // ===============================
    // ELIMINAR
    // ===============================
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        productoRepository.deleteById(id);
    }
}
