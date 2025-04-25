package br.com.cdb.bancodigital.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	private static final String REGEX_CPF = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
	
	private boolean validarNascimento(LocalDate nascimento) {
        try {
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            //LocalDate dataNascimento = LocalDate.parse(nascimento, formatter);
            LocalDate hoje = LocalDate.now();
            int idade = Period.between(nascimento, hoje).getYears();

            if (idade >= 18) 
            	return true;            

        } catch (DateTimeParseException e) {
            System.out.println("Erro: Data inválida! Use o formato dd/MM/yyyy.");            
        }
        return false;
	}	
	
	private boolean validarNome(String nome) {
		try
		{
			if (nome == null || nome.isEmpty())	{
				throw new IllegalArgumentException("O texto não pode ser nulo ou vazio.");
			}
			
			if (!nome.matches("[a-zA-ZÀ-ÖØ-öø-ÿ ]+")) {
                throw new Exception("O texto contém caracteres inválidos.");
            }	
			
			if (nome.length() < 2 || nome.length() > 100) {
				throw new Exception("Comprimento inválido.");	
			}
			
			return true;
		} catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
		
		return false;	
	}	
	
	private boolean validarCPF(String cpf) {
        try {
            // Verifica se o CPF está no formato correto
            if (!Pattern.matches(REGEX_CPF, cpf)) {
                throw new IllegalArgumentException("Formato de CPF inválido.");
            }

            // Remove os caracteres especiais para validação (mantém apenas números)
            String cpfNumerico = cpf.replaceAll("[^0-9]", "");

            // Verifica se todos os números são iguais (ex: 111.111.111-11), o que é inválido
            if (cpfNumerico.matches("(\\d)\\1{10}")) {
                throw new IllegalArgumentException("CPF inválido: todos os números são iguais.");
            }

            // Verifica os dígitos verificadores
            if (!validarDigitos(cpfNumerico)) {
                throw new IllegalArgumentException("CPF inválido: dígitos verificadores incorretos.");
            }

            return true; 

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());            
        }    
        return false;
	}
	
    private static boolean validarDigitos(String cpf) {
        int soma1 = 0, soma2 = 0;
        int peso1 = 10, peso2 = 11;

        // Cálculo do primeiro e segundo dígito verificador
        for (int i = 0; i < 9; i++) {
            int num = cpf.charAt(i) - '0';
            soma1 += num * peso1--;
            soma2 += num * peso2--;
        }

        // Primeiro dígito verificador
        int digito1 = (soma1 * 10) % 11;
        if (digito1 == 10) digito1 = 0;

        // Adiciona o primeiro dígito na soma do segundo cálculo
        soma2 += digito1 * peso2;

        // Segundo dígito verificador
        int digito2 = (soma2 * 10) % 11;
        if (digito2 == 10) digito2 = 0;

        // Verifica se os dígitos batem com os do CPF informado
        return (digito1 == (cpf.charAt(9) - '0')) && (digito2 == (cpf.charAt(10) - '0'));
    }	
	
	private void validarCampos(String nome, Long cpf, LocalDate nascimento, String cep) {
	    //if (nome == null || nome.trim().isEmpty()) {
		if (!validarNome(nome)) {
	        throw new IllegalArgumentException("Nome é obrigatório.");
	    }

	    //if (cpf == null || cpf.toString().length() != 11) {
		String cpfString = cpf.toString();
		if (!validarCPF(cpfString)) {
	        throw new IllegalArgumentException("CPF inválido.");
	    }

	    //if (nascimento == null || nascimento.isAfter(LocalDate.now())) {
	    if (!validarNascimento(nascimento)) {
	        throw new IllegalArgumentException("Data de nascimento inválida.");
	    }

	    if (cep == null || !cep.matches("\\d{8}")) {
	        throw new IllegalArgumentException("CEP inválido.");
	    }
	}	
	
	public Cliente salvarCliente(String nome, Long cpf, LocalDate nascimento, String rua, String numero, String complemento, String cidade, String estado, String cep, String tipo)
	{
		validarCampos(nome, cpf, nascimento, cep);
		
		//validar os campos
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome(nome);
		cliente.setNascimento(nascimento);
		cliente.setRua(rua);
		cliente.setNumero(numero);
		cliente.setComplemento(complemento);
		cliente.setCidade(cidade);
		cliente.setEstado(estado);
		cliente.setCep(cep);
		cliente.setTipo(tipo);

		return clienteRepository.save(cliente);
	}
	
	//public Cliente removerCliente(Long id)
	//{
	//	Cliente cliente = new Cliente();		
	//	return clienteRepository.deleteById(cliente.getId());
	//}
	
	public List<Cliente> getClientes()
	{
		return clienteRepository.findAll();
	}
}
