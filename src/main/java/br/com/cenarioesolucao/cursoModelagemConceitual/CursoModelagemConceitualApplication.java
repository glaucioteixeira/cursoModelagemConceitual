package br.com.cenarioesolucao.cursoModelagemConceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Categoria;
import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Cidade;
import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Cliente;
import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Endereco;
import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Estado;
import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Produto;
import br.com.cenarioesolucao.cursoModelagemConceitual.domain.enums.TipoCliente;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.CategoriaRepository;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.CidadeRepository;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.ClienteRepository;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.EnderecoRepository;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.EstadoRepository;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoModelagemConceitualApplication implements CommandLineRunner {

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
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoModelagemConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "INFORMÁTICA");
		Categoria cat2 = new Categoria(null, "ESCRITÓRIO");
		
		Produto p1 = new Produto(null, "COMPUTADOR", 2000.00);
		Produto p2 = new Produto(null, "IMPRESSORA", 800.00);
		Produto p3 = new Produto(null, "MOUSE", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "MINAS GERAIS");
		Estado est2 = new Estado(null, "SÃO PAULO");
		
		Cidade c1 = new Cidade(null, "UBERLÂNDIA", est1);
		Cidade c2 = new Cidade(null, "SÃO PAULO", est2);
		Cidade c3 = new Cidade(null, "CAMPINAS", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
}
