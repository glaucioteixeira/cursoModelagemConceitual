package br.com.cenarioesolucao.cursoModelagemConceitual.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cenarioesolucao.cursoModelagemConceitual.domain.ItemPedido;
import br.com.cenarioesolucao.cursoModelagemConceitual.domain.PagamentoComBoleto;
import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Pedido;
import br.com.cenarioesolucao.cursoModelagemConceitual.domain.enums.EstadoPagamento;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.ItemPedidoRepository;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.PagamentoRepository;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.PedidoRepository;
import br.com.cenarioesolucao.cursoModelagemConceitual.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired 
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		//return obj.orElse(null);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido Insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.buscar(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
		
	}
	
}
