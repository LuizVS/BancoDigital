💳 Banco Digital - API Spring Boot

Este projeto é uma API de um Banco Digital desenvolvida em Java utilizando Spring Boot e banco de dados H2.
A aplicação permite o cadastro e gerenciamento de clientes, contas bancárias, cartões de crédito e débito, e seguros para cartões.

📚 Funcionalidades Implementadas
* Cadastro de clientes com validação de CPF, nome, nascimento e endereço
* Criação de contas correntes e contas poupança
* Emissão de cartões de crédito e débito
* Controle de limites de cartões e contas
* Gerenciamento de seguros para cartões de crédito (Viagem e Fraude)
* Endpoints expostos para testes via Postman ou outras ferramentas de API

🛠️ Tecnologias Utilizadas
* Java 17+
* Spring Boot
* Spring Data JPA
* Banco de Dados H2 (em memória)
* Maven
* Postman (para testes dos endpoints)

🚀 Como Rodar o Projeto Localmente
1. Clone o repositório:
   git clone https://github.com/LuizVS/BancoDigital.git
2. Importe o projeto em sua IDE (Eclipse, IntelliJ, VS Code...).
3. Execute a aplicação como Spring Boot Application.
4. O sistema ficará disponível em:
   http://localhost:8080
5. Acesse o console do banco H2 (opcional):
   http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:testdb
   Username: sa
   Password: (deixe em branco)

📬 Endpoints da API Banco Digital

📄 Cliente
* POST /clientes → Criar um novo cliente
* GET /clientes/{id} → Buscar um cliente específico pelo ID
* PUT /clientes/{id} → Atualizar dados de um cliente
* DELETE /clientes/{id} → Remover um cliente
* GET /clientes → Listar todos os clientes

🏦 Conta
* POST /contas/corrente → Criar uma conta corrente
* POST /contas/poupanca → Criar uma conta poupança
* GET /contas/{id} → Buscar conta por ID
* PUT /contas/transferir → Transferir saldo entre contas
* PUT /contas/depositar → Depositar valor em uma conta
* PUT /contas/sacar → Sacar valor de uma conta
* PUT /contas/pix → Realizar um Pix entre contas

💳 Cartão
* POST /cartoes/credito → Emitir um novo cartão de crédito
* POST /cartoes/debito → Emitir um novo cartão de débito
* PUT /cartoes/{id}/alterar-senha → Alterar senha de cartão
* PUT /cartoes/{id}/ativar → Ativar um cartão
* PUT /cartoes/{id}/desativar → Desativar um cartão
* GET /cartoes/{id} → Consultar dados de um cartão

🛡️ Seguro
* POST /seguros/emitir → Emitir apólice de seguro para cartão de crédito
* GET /seguros/{id} → Consultar seguro emitido para um cartão

🧩 Regras de Negócio Específicas
* CPF único e validado no momento do cadastro do cliente.
* Clientes são classificados como Comum, Super ou Premium.
* Cartões de Crédito:
  * Limite pré-aprovado baseado no tipo de cliente.
  * Aplicação de taxa de utilização se ultrapassar 80% do limite.
* Cartões de Débito:
  * Limite diário de transações ajustável.
* Seguros de Cartão de Crédito:
  * Seguro Viagem: gratuito para Premium, pago para outros.
  * Seguro Fraude: automático para todos os cartões com cobertura de R$5.000.

🧪 Testes
* A API pode ser testada usando o Postman ou qualquer cliente HTTP.
* Todos os endpoints retornam respostas claras e mensagens de erro de validação.

📄 Licença

Este projeto está sob a licença MIT.

🎯 Observação Final

Esse projeto foi desenvolvido como parte de estudo para prática de arquitetura em camadas no Spring Boot e boas práticas de desenvolvimento de API RESTful.

👤 Autor

[@LuizVS](https://github.com/LuizVS/BancoDigital/commits?author=LuizVS)
