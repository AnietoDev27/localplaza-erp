package com.localplaza.erp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.localplaza.erp.model.Categoria;
import com.localplaza.erp.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/api/categoria")
    public List<Categoria> getCatergoria() {
        return categoriaService.listCategorias();
    }

    @GetMapping("/api/categoria/{id}")
    public ResponseEntity<Categoria> getCatergoriaId(@PathVariable Long id) {
        Optional<Categoria> aux = categoriaService.buscarPorId(id);
        return !aux.isEmpty() ? new ResponseEntity<>(aux.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/categoria")
    public ResponseEntity<HttpStatus> createCatergoria(@RequestBody @Valid Categoria categoria) {
        Boolean aux = categoriaService.crearCategoria(categoria.getNombre(), categoria.getUnidadMedida());
        return aux ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
