package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO {

    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public CategoriaDTO(Integer id, @NotEmpty(message = "Preenchimento obrigatorio") @Length(min = 5, message = "Nome menor que 5 caracteres") String nome) {
        this.id = id;
        this.nome = nome;
    }

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 5, message = "Nome menor que 5 caracteres")
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
