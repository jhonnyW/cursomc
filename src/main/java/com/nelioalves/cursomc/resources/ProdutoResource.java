package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.dto.ProdutoDTO;
import com.nelioalves.cursomc.resources.exception.ResourceExceptionHandler;
import com.nelioalves.cursomc.resources.utils.URL;
import com.nelioalves.cursomc.services.PedidoService;
import com.nelioalves.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource extends ResourceExceptionHandler{
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)  {
		Produto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
													 @RequestParam(value = "categorias" ) String categorias,
													 @RequestParam( value = "page", defaultValue = "0") Integer page,
													 @RequestParam( value = "linePerPage", defaultValue = "24")Integer linesPerPage,
													 @RequestParam( value = "direction", defaultValue = "ASC")String direction,
													 @RequestParam( value = "orderBy", defaultValue = "nome")String orderBy
													 ) {
		List<Integer> listaIds = URL.decodeIntList(categorias);
		Page<Produto> listaProduto= service.search(URL.decodeParam(nome),listaIds,page,linesPerPage,orderBy,direction);
		Page<ProdutoDTO> listaDTO = listaProduto.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listaDTO);

	}
}
