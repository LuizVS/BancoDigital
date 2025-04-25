package br.com.cdb.bancodigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigital.entity.SeguroCartao;

@Repository
public interface SeguroCartaoRepository extends JpaRepository<SeguroCartao, Long> {

}
