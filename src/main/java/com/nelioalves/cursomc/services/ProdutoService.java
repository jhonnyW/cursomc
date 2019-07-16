package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;
import com.nelioalves.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repo;
    @Autowired
    private CategoriaRepository repoCategorias;

    public Produto buscar(Integer id) throws ObjectNotFoundException {
        Optional<Produto> produto = repo.findById(id);

        return produto.orElseThrow(() -> new ObjectNotFoundException
                ("Objeto não encontrado! Id: " + id));

    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias =  repoCategorias.findAllById(ids);

        return  repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias ,pageRequest);
    }



}
