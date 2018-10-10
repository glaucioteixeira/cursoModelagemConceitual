package br.com.cenarioesolucao.cursoModelagemConceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Cliente;
import br.com.cenarioesolucao.cursoModelagemConceitual.repositories.ClienteRepository;
import br.com.cenarioesolucao.cursoModelagemConceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		//return obj.orElse(null);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
