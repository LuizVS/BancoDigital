package br.com.cdb.bancodigital.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cdb.bancodigital.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
