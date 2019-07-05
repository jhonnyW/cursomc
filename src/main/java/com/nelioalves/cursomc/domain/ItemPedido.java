package com.nelioalves.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;
@Entity
public class ItemPedido {
    private Double desconto;
    private Integer quantidade;
    private Double preco;
    @EmbeddedId
    @JsonIgnore
    private ItemPedidoPK id = new ItemPedidoPK();

    public ItemPedido() {
    }

    public ItemPedido(Double desconto, Integer quantidade, Double preco, Pedido pedido, Produto produto) {
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
@JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }
    public Produto getProduto(){
        return id.getProduto();
    }
    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }
}
