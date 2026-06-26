Backend desenvolvido para o projeto WTC, com foco em uma plataforma de comunicação integrada ao CRM. A aplicação permite autenticação de usuários, gerenciamento de clientes, registro de anotações e troca de mensagens entre usuários. 

 

**Tecnologias Utilizadas **

- Java 21 
- Spring Boot 3.4.3 
- Spring Web 
- Spring Security 
- Spring Data MongoDB 
- JWT 
- Swagger/OpenAPI 
- Maven 

 
**
Funcionalidades Implementadas **

- Cadastro de usuários 
- Login com geração de token JWT 
- Consulta do usuário autenticado 
- Listagem, busca e remoção de usuários 
- Cadastro, listagem, edição e remoção de clientes 
- Busca de clientes por nome, status, tag e score mínimo 
- Inclusão de anotações em clientes 
- Envio de mensagens entre usuários 
- Consulta de conversa por conversationId 
- Consulta de inbox por usuário 
- Atualização de status da mensagem 
- Proteção de rotas com autenticação JWT 
- Documentação da API via Swagger 

**Como Executar **

**Pré-requisitos **

Java 21 instalado 

Maven instalado ou uso do Maven Wrapper 

Acesso a uma base MongoDB 

**Configuração **

As configurações principais ficam em: 

src/main/resources/application.properties 

Configure as variáveis de conexão com o MongoDB, chave JWT, tempo de expiração do token e porta da aplicação conforme o ambiente de execução. 

**Exemplo recomendado: **

spring.application.name=wtc 

spring.data.mongodb.uri=SUA_URI_DO_MONGODB 

jwt.secret=SUA_CHAVE_SECRETA 

jwt.expiration=86400000 

server.port=8080 

springdoc.swagger-ui.path=/swagger-ui.html 

Executando o projeto 

No terminal, dentro da pasta do projeto, execute: 

./mvnw spring-boot:run 

Ou, caso tenha o Maven instalado: 

mvn spring-boot:run 

A API será executada em: 

http://localhost:8080 

A documentação Swagger estará disponível em: 

http://localhost:8080/swagger-ui.html 

Autenticação 

As rotas de autenticação são públicas. As demais rotas exigem token JWT no cabeçalho da requisição. 

Formato do header: 

Authorization: Bearer SEU_TOKEN_JWT 

Endpoints 

Autenticação 

POST /auth/register 

POST /auth/login 

GET /auth/me 

Usuários 

GET /users 

GET /users/{id} 

DELETE /users/{id} 

Clientes 

POST /clients 

GET /clients 

GET /clients/{id} 

PUT /clients/{id} 

DELETE /clients/{id} 

POST /clients/{id}/notes 

GET /clients/search 

**Parâmetros disponíveis para busca de clientes: **

name
status 
tag 
minScore 

**Exemplo: **

GET /clients/search?name=Maria&status=ACTIVE&tag=vip&minScore=50 

**Mensagens **

POST /messages 

GET /messages/conversation/{conversationId} 

GET /messages/inbox/{userId} 

PATCH /messages/{id}/status 

Exemplos de Payload 

**Cadastro de usuário **

{ 

  "name": "Operador WTC", 

  "email": "operador@wtc.com", 

  "password": "123456", 

  "role": "OPERATOR" 

} 

**Login **

{ 

  "email": "operador@wtc.com", 

  "password": "123456" 

} 

**Cadastro de cliente **

{ 

  "name": "Maria Silva", 

  "email": "maria@email.com", 

  "phone": "11999999999", 

  "tags": ["vip", "evento"], 

  "score": 80, 

  "status": "ACTIVE" 

} 

**Adicionar anotação ao cliente 
**
{ 

  "text": "Cliente demonstrou interesse em campanhas de eventos." 

} 

Envio de mensagem 

{ 

  "fromUserId": "ID_USUARIO_ORIGEM", 

  "toUserId": "ID_USUARIO_DESTINO", 

  "content": "Olá, temos uma nova campanha disponível.", 

  "messageType": "TEXT" 

} 

Atualização de status da mensagem 

{ 

  "status": "READ" 

} 

Modelos Principais 

User 
Representa os usuários da aplicação, com nome, e-mail, senha criptografada, role, status ativo e data de criação. 

Client 
Representa os clientes do CRM, com nome, e-mail, telefone, tags, score, status, anotações e data de criação. 

Message 
Representa as mensagens trocadas entre usuários, contendo remetente, destinatário, conteúdo, tipo da mensagem, status, conversationId e data de criação. 

Observações 
Este backend implementa a base funcional para autenticação, CRM de clientes e mensageria. Funcionalidades como campanhas, segmentos, push notification, WebSocket e upload de imagens podem ser adicionadas em evoluções futuras do projeto. 

 
 

 
