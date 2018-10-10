package br.com.cenarioesolucao.cursoModelagemConceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cenarioesolucao.cursoModelagemConceitual.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
