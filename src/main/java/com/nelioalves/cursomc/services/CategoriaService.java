package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.service.exception.ObjectNotFoundException;

 
@Service
public class CategoriaService {
	@Autowired	
private CategoriaRepository repo;
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> cat =repo.findById(id);
		
		return cat.orElseThrow(() -> new ObjectNotFoundException
				("Objeto não encontrado! Id: " + id ));
			
	}
	public Categoria insert(Categoria obj){

		return repo.save(obj);
	}
	public void delete(Integer id){

		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException exp){
			throw  new DataIntegrityException("Not possible exclude Category with Items");
		}

	}
	public List<Categoria> listarTodos(){
		return repo.findAll();

	}
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return  repo.findAll(pageRequest);
	}
	public Categoria fromDTO(CategoriaDTO categoriaDTO){
	return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
}
