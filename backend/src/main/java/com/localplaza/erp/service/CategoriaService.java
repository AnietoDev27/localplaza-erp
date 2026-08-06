package com.localplaza.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.localplaza.erp.repository.CategoriaRepository;
import com.localplaza.erp.model.Categoria;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listCategorias() {
        return this.categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return this.categoriaRepository.findById(id);
    }

    public Boolean crearCategoria(String nombre, String unidadMedida) {
        if (this.categoriaRepository.findByNombre(nombre).isEmpty()) {
            Categoria aux = new Categoria();
            aux.setNombre(nombre);
            aux.setUnidadMedida(unidadMedida);
            this.categoriaRepository.save(aux);
            return true;
        }
        return false;
    }
}
