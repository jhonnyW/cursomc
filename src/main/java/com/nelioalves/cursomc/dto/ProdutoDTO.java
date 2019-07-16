package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.services.validation.ClienteInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

 public class ProdutoDTO implements Serializable {
     private static final long serialVersionUID = -256613747062213146L;
     private Integer id;
     private String nome;
     private Double preco;

     public ProdutoDTO(Produto obj) {
         id = obj.getId();
         nome = obj.getNome();
         preco = obj.getPreco();
     }

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

     public Double getPreco() {
         return preco;
     }

     public void setPreco(Double preco) {
         this.preco = preco;
     }

     public ProdutoDTO(Integer id) {
         this.id = id;
     }
 }
