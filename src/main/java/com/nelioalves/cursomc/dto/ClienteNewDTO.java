package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.services.validation.ClienteInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@ClienteInsert
public class ClienteNewDTO implements Serializable {
    private static final long serialVersionUID = -3795624432697467843L;
    private Integer id;
    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(message = "Tamanho Maior que 5", min = 5, max = 120)
    private String nome;
    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Email(message = "email Invalido")
    private String email;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String cpfOuCnpj;
    private Integer tipo;
    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String logradouro;
    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String numero;
    private String complemento;
    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String bairro;
    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String cep;
    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeid;

    public ClienteNewDTO(Integer id, @NotEmpty(message = "Preenchimento Obrigatorio") @Length(message = "Tamanho Maior que 5", min = 5, max = 120) String nome, @NotEmpty(message = "Preenchimento Obrigatorio") @Email(message = "email Invalido") String email, String cpfOuCnpj, Integer tipo, String logradouro, String numero, String complemento, String bairro, String cep, String telefone1, String telefone2, String telefone3, Integer cidadeid) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.telefone3 = telefone3;
        this.cidadeid = cidadeid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Integer getCidadeid() {
        return cidadeid;
    }

    public void setCidadeid(Integer cidadeid) {
        this.cidadeid = cidadeid;
    }

    public ClienteNewDTO() {
    }


}
