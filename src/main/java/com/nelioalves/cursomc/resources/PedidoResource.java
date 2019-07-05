package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.resources.exception.ResourceExceptionHandler;
import com.nelioalves.cursomc.services.CategoriaService;
import com.nelioalves.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource extends ResourceExceptionHandler{
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)  {
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}
}
