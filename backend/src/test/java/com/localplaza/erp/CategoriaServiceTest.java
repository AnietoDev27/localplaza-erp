package com.localplaza.erp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.localplaza.erp.repository.CategoriaRepository;
import com.localplaza.erp.service.CategoriaService;

import com.localplaza.erp.model.Categoria;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRepository;
    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void noCrearCategoriadDuplicada() {

        Categoria aux = new Categoria();
        aux.setNombre("Herramientas");
        aux.setUnidadMedida("stock");
        Mockito.when(categoriaRepository.findByNombre(aux.getNombre()))
                .thenReturn(Optional.of(aux));
        boolean resultado = categoriaService.crearCategoria(aux.getNombre(), aux.getUnidadMedida());
        assertFalse(resultado);

    }
}
