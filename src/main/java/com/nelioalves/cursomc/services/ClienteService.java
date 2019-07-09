package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.service.exception.ObjectNotFoundException;

 
@Service
public class ClienteService {
	@Autowired	
private ClienteRepository repo;
	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> cat =repo.findById(id);
		
		return cat.orElseThrow(() -> new ObjectNotFoundException
				("Objeto não encontrado! Id: " + id ));
			
	}
	public Cliente insert(Cliente obj){

		return repo.save(obj);
	}
	public void delete(Integer id){

		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException exp){
			throw  new DataIntegrityException("Cliente com pedidos");
		}

	}
	public List<Cliente> listarTodos(){
		return repo.findAll();

	}
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return  repo.findAll(pageRequest);
	}
    public Cliente fromDTO(ClienteDTO objDTO) {
		return  new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail());
    }
}
