package com.example.backend_cafedronel.service;

import com.example.backend_cafedronel.model.Producto;
import com.example.backend_cafedronel.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private final String UPLOAD_DIR = "uploads/";

    // ============================
    //   GUARDAR ARCHIVO IMAGEN
    // ============================
    public String guardarImagen(MultipartFile imagen) throws IOException {
        if (imagen == null || imagen.isEmpty()) return null;

        File carpeta = new File(UPLOAD_DIR);
        if (!carpeta.exists()) carpeta.mkdirs();

        String nombreArchivo =
                System.currentTimeMillis() + "_" + imagen.getOriginalFilename();

        Path ruta = Paths.get(UPLOAD_DIR + nombreArchivo);

        Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/" + nombreArchivo;
    }

    // ============================
    //     CREAR PRODUCTO
    // ============================
    public Producto crear(Producto p, MultipartFile imagen) throws IOException {

        if (imagen != null && !imagen.isEmpty()) {
            p.setImagen(guardarImagen(imagen));
        }

        return productoRepository.save(p);
    }

    // ============================
    //     ACTUALIZAR PRODUCTO
    // ============================
    public Producto actualizar(Integer id, Producto datos, MultipartFile imagen) throws IOException {

        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        p.setNombre(datos.getNombre());
        p.setDescripcion(datos.getDescripcion());
        p.setTamano(datos.getTamano());
        p.setPrecio(datos.getPrecio());
        p.setCategoria(datos.getCategoria());
        p.setCantidad(datos.getCantidad());
        p.setDestacado(datos.getDestacado());
        p.setActivo(datos.getActivo());
        p.setStockMinimo(datos.getStockMinimo());
        p.setStockMaximo(datos.getStockMaximo());
        p.setStockCritico(datos.getStockCritico());

        if (imagen != null && !imagen.isEmpty()) {
            p.setImagen(guardarImagen(imagen));
        }

        return productoRepository.save(p);
    }
}
