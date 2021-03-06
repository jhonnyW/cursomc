package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@ClienteUpdate
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = -7456214069136999457L;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente ob) {
        this.id = ob.getId();
        this.email = ob.getEmail();
        this.nome = ob.getNome();
    }

    public ClienteDTO(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    private Integer id;
    @NotEmpty(message="Preenchimento Obrigatorio")@Length(message = "Tamanho Maior que 5",min = 5,max= 120)
    private String nome;
    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Email(message = "email Invalido")
    private String email;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
