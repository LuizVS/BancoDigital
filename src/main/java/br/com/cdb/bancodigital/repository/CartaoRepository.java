package br.com.cdb.bancodigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigital.entity.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long>{

}
