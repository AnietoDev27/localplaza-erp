package com.localplaza.erp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.localplaza.erp.service.ProveedorService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.localplaza.erp.model.Proveedor;

@RestController
public class ProveedorController {
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/api/proveedor")
    public List<Proveedor> getProveedor() {
        return this.proveedorService.listarProveedores();
    }

    @GetMapping("/api/proveedor/{id}")
    public ResponseEntity<Proveedor> getProveedorPorId(@PathVariable Long id) {
        Optional<Proveedor> aux = proveedorService.buscarProveedor(id);
        return !aux.isEmpty() ? new ResponseEntity<>(aux.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/proveedor")
    public ResponseEntity<HttpStatus> createProveedor(@RequestBody @Valid Proveedor proveedor) {
        Boolean aux = proveedorService.crearProveedor(proveedor.getNombre(), proveedor.getContacto(),
                proveedor.getEmail(), proveedor.getTelefono());
        return aux ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
