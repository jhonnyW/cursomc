package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.resources.exception.ResourceExceptionHandler;
import com.nelioalves.cursomc.services.ClienteService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource  extends ResourceExceptionHandler{
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)  {
		
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDTO) {
		Cliente obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objClienteDTO, @PathVariable Integer id) {
		Cliente obj = service.buscar(id);
 		obj.setId(id);
 		obj.setNome(objClienteDTO.getNome());
 		obj.setEmail(objClienteDTO.getEmail());
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}



	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> listaClientes = service.listarTodos();
		List<ClienteDTO> listaDTO = listaClientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);

	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam( value = "page", defaultValue = "0") Integer page,
													   @RequestParam( value = "linePerPage", defaultValue = "24")Integer linesPerPage,
													   @RequestParam( value = "direction", defaultValue = "ASC")String direction,
													   @RequestParam( value = "orderBy", defaultValue = "nome")String orderBy) {

		Page<Cliente> listaClientes = service.findPage(page,linesPerPage,orderBy,direction);
		Page<ClienteDTO> listaDTO = listaClientes.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaDTO);

	}
}
