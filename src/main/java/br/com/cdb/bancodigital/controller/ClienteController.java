package br.com.cdb.bancodigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		List<Cliente> clientes = clienteService.listarClientes();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id);
    }	
    
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @Validated @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }    
    
    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }    
	
}
