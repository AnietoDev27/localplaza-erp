package com.localplaza.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.localplaza.erp.model.Proveedor;
import com.localplaza.erp.repository.ProveedorRepository;

@Service
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> listarProveedores() {
        return this.proveedorRepository.findAll();
    }

    public Optional<Proveedor> buscarProveedor(Long id) {
        return this.proveedorRepository.findById(id);
    }

    public boolean crearProveedor(String nombre, String contacto, String email, String telefono) {
        if (this.proveedorRepository.findByNombre(nombre).isEmpty()) {
            if ((telefono == null || telefono.isBlank()) && (email == null || email.isBlank())) {
                return false;
            } else {
                Proveedor aux = new Proveedor();
                aux.setNombre(nombre);
                aux.setContacto(contacto);
                aux.setEmail(email);
                aux.setTelefono(telefono);
                this.proveedorRepository.save(aux);
                return true;
            }
        }
        return false;
    }
}
