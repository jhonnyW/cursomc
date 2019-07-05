package com.nelioalves.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.nelioalves.cursomc.domain.*;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.enums.TipoCliente;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
    @Autowired
    private CategoriaRepository catRepo;
    @Autowired
    private ProdutoRepository prodRepo;
    @Autowired
    private EstadoRepository estRepo;
    @Autowired
    private CidadeRepository cidRepo;
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private EnderecoRepository endRepo;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Produto pr1 = new Produto(null, "Computador", 2000.00);
        Produto pr2 = new Produto(null, "Impressora", 800.00);
        Produto pr3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(pr1, pr2, pr3));
        cat2.getProdutos().add(pr2);

        pr1.getCategorias().add(cat1);
        pr2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        pr3.getCategorias().add(cat1);

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");


        Cidade c1 = new Cidade(null, "São Paulo", est2);
        Cidade c2 = new Cidade(null, "Uberlandia", est1);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().add(c1);
        est2.getCidades().addAll(Arrays.asList(c2, c3));
        catRepo.saveAll(Arrays.asList(cat1, cat2));
        prodRepo.saveAll(Arrays.asList(pr1, pr2, pr3));

        estRepo.saveAll(Arrays.asList(est1, est2));
        cidRepo.saveAll(Arrays.asList(c1, c2, c3));
        Cliente cli1 = new Cliente(null, "Maria Silva", "maria_silva@gmail.com", "66666666666", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("999953", "546546566"));
        Endereco e1 = new Endereco(null, "Rua Flores", "300", "apt 303", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "38220834", cli1, c2);
        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepo.save(cli1);
        endRepo.saveAll(Arrays.asList(e1, e2));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 10:32"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	    pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));

	    ItemPedido ip1 = new ItemPedido(0.0,1,2000.00,ped1,pr1);
	    ItemPedido ip2 = new ItemPedido(0.0,2,80.00,ped1,pr3);
	    ItemPedido ip3 = new ItemPedido(100.0,1,800.00,ped2,pr2);

	    ped1.getItems().addAll(Arrays.asList(ip1,ip2));
	    ped2.getItems().add(ip3);


        pr1.getItems().add(ip1);
        pr2.getItems().add(ip3);
        pr3.getItems().add(ip2);

        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
    }
}
