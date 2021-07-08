package com.geibatista.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.geibatista.cursomc.domain.Categoria;
import com.geibatista.cursomc.domain.Cidade;
import com.geibatista.cursomc.domain.Cliente;
import com.geibatista.cursomc.domain.Endereco;
import com.geibatista.cursomc.domain.Estado;
import com.geibatista.cursomc.domain.Pagamento;
import com.geibatista.cursomc.domain.PagamentoComBoleto;
import com.geibatista.cursomc.domain.PagamentoComCartao;
import com.geibatista.cursomc.domain.Pedido;
import com.geibatista.cursomc.domain.Produto;
import com.geibatista.cursomc.domain.enums.EstadoPagamento;
import com.geibatista.cursomc.domain.enums.TipoCliente;
import com.geibatista.cursomc.repositories.CategoriaRepository;
import com.geibatista.cursomc.repositories.CidadeRepository;
import com.geibatista.cursomc.repositories.ClienteRepository;
import com.geibatista.cursomc.repositories.EnderecoRepository;
import com.geibatista.cursomc.repositories.EstadoRepository;
import com.geibatista.cursomc.repositories.PagamentoRepository;
import com.geibatista.cursomc.repositories.PedidoRepository;
import com.geibatista.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Barbara Sarno", "barbara.sarno@claro.com.br", "94070369520", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "Rua Humberto Porto", "193", "Apto 104", "São Marcos", "41250575", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua das Araras", "1057", "Apto 1010", "Imbuí", "42510825", cli1, c2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"),cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
				
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cli1.getTelefones().addAll(Arrays.asList("71981041151", "7130426141"));
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		ped1.setPagamento(pgto1);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		
	}
}
