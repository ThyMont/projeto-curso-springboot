package com.thymont;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thymont.domain.Categoria;
import com.thymont.domain.Cidade;
import com.thymont.domain.Cliente;
import com.thymont.domain.Endereco;
import com.thymont.domain.Estado;
import com.thymont.domain.ItemPedido;
import com.thymont.domain.Pagamento;
import com.thymont.domain.PagamentoComBoleto;
import com.thymont.domain.PagamentoComCartao;
import com.thymont.domain.Pedido;
import com.thymont.domain.Produto;
import com.thymont.domain.enums.EstadoPagamento;
import com.thymont.domain.enums.TipoCliente;
import com.thymont.repositories.CategoriaRepository;
import com.thymont.repositories.CidadeRepository;
import com.thymont.repositories.ClienteRepository;
import com.thymont.repositories.EnderecoRepository;
import com.thymont.repositories.EstadoRepository;
import com.thymont.repositories.ItemPedidoRepository;
import com.thymont.repositories.PagamentoRepository;
import com.thymont.repositories.PedidoRepository;
import com.thymont.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Pets");
		Categoria cat4 = new Categoria(null, "Piscina");
		Categoria cat5 = new Categoria(null, "Casa");
		Categoria cat6 = new Categoria(null, "Cozinha");
		Categoria cat7 = new Categoria(null, "Papelaria");
		Categoria cat8 = new Categoria(null, "Brinquedos");

		Produto p1 = new Produto(null, "Computador", 3000.00);
		Produto p2 = new Produto(null, "Impressora", 3000.00);
		Produto p3 = new Produto(null, "Mouse", 3000.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Pará");
		Estado est2 = new Estado(null, "Rio de Janeiro");

		Cidade c1 = new Cidade(null, "Belém", est1);
		Cidade c2 = new Cidade(null, "Ananindeua", est1);
		Cidade c3 = new Cidade(null, "Rio de Janeiro", est2);

		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Thyago Monteiro", "thy@gmail.com", "12345678911", TipoCliente.PESSOA_FISICA);

		cli1.getTelefones().addAll(Arrays.asList("21 969696966"));
		cli1.getTelefones().addAll(Arrays.asList("21 969696969"));

		Endereco e1 = new Endereco(null, "Cabuçu de baixo", "257", "R3 L8 Q e", "Guaratiba", "23036060", c3, cli1);
		Endereco e2 = new Endereco(null, "Rio Douro", "108", "bl 9b, set 2", "Icoaraci", "66820475", c1, cli1);

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("26/11/2023 11:26"), cli1, e2);
		Pedido ped2 = new Pedido(null, sdf.parse("07/01/2024 13:07"), cli1, e1);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/01/2024 13:07"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
