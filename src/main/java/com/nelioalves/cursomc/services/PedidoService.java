package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id) throws ObjectNotFoundException {
        Optional<Pedido> pedido = repo.findById(id);

        return pedido.orElseThrow(() -> new ObjectNotFoundException
                ("Objeto não encontrado! Id: " + id));

    }
}
