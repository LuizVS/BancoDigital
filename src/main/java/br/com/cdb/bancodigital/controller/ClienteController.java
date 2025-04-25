package br.com.cdb.bancodigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addCliente(@RequestBody Cliente cliente)
	{
		Cliente clienteAdicionado = clienteService.salvarCliente(cliente.getNome(), cliente.getCpf(), cliente.getNascimento(),
				                                                 cliente.getRua(), cliente.getNumero(), cliente.getComplemento(),
				                                                 cliente.getCidade(), cliente.getEstado(), cliente.getCep(), cliente.getTipo());		
		
		if (clienteAdicionado != null) {
			return new ResponseEntity<>("Cliente " + cliente.getNome() + " Adicionado com Sucesso!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Dados do Cliente Inválidos!", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Cliente>> getAllClientes()
	{
		List<Cliente> clientes = clienteService.getClientes();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
}
