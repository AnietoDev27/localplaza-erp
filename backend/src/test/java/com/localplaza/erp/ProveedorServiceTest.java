package com.localplaza.erp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.localplaza.erp.model.Proveedor;
import com.localplaza.erp.repository.ProveedorRepository;
import com.localplaza.erp.service.ProveedorService;

@ExtendWith(MockitoExtension.class)
public class ProveedorServiceTest {
    @Mock
    private ProveedorRepository proveedorRepository;
    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void noCrearProveedorDuplicado() {

        Proveedor aux = new Proveedor();
        aux.setNombre("Envios Frutero Sanchez");
        aux.setContacto("Ferran Sanchez");
        aux.setEmail("Fsanchez71@FruteroSanchez.com");
        aux.setTelefono("900 111 111");
        Mockito.when(proveedorRepository.findByNombre(aux.getNombre()))
                .thenReturn(Optional.of(aux));
        boolean resultado = proveedorService.crearProveedor(aux.getNombre(), aux.getContacto(), aux.getEmail(),
                aux.getTelefono());
        assertFalse(resultado);
    }

    @Test
    void noCrearSinTelefonoYEmailNull() {
        Proveedor aux = new Proveedor();
        aux.setNombre("Envios Frutero Sanchez");
        aux.setContacto("Nicolas Smith");
        boolean resultado = proveedorService.crearProveedor(aux.getNombre(), aux.getContacto(), null, null);
        assertFalse(resultado);
    }

    @Test
    void noCrearSinTelefonoYEmailVacio() {
        Proveedor aux = new Proveedor();
        aux.setNombre("Envios Frutero Sanchez");
        aux.setContacto("Nicolas Smith");
        boolean resultado = proveedorService.crearProveedor(aux.getNombre(), aux.getContacto(), "", "");
        assertFalse(resultado);
    }

    @Test
    void crearProveedor() {
        Proveedor aux = new Proveedor();
        aux.setNombre("Envios Frutero Sanchez");
        aux.setContacto("Ferran Sanchez");
        aux.setEmail("Fsanchez71@FruteroSanchez.com");
        aux.setTelefono("900 111 111");
        Mockito.when(proveedorRepository.findByNombre(aux.getNombre()))
                .thenReturn(Optional.empty());
        boolean resultado = proveedorService.crearProveedor(aux.getNombre(), aux.getContacto(), aux.getEmail(),
                aux.getTelefono());
        Mockito.verify(proveedorRepository, Mockito.times(1)).save(Mockito.any(Proveedor.class));
        assertTrue(resultado);
    }
}
