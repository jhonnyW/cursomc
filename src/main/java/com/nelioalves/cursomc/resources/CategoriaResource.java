package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.resources.exception.ResourceExceptionHandler;
import com.nelioalves.cursomc.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource extends ResourceExceptionHandler {
    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Categoria obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {

        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> update(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll(@PathVariable Integer id) {

        List<Categoria> listaCategorias = service.listarTodos();
        List<CategoriaDTO> listaDTO = listaCategorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);

    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam( value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam( value = "linePerPage", defaultValue = "24")Integer linesPerPage,
                                                       @RequestParam( value = "direction", defaultValue = "ASC")String direction,
                                                       @RequestParam( value = "orderBy", defaultValue = "nome")String orderBy) {

        Page<Categoria> listaCategorias = service.findPage(page,linesPerPage,orderBy,direction);
        Page<CategoriaDTO> listaDTO = listaCategorias.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listaDTO);

    }

}
