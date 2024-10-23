# BancoDeDadosAndroid

## Descrição

Este projeto é um aplicativo Android que permite o cadastro de alunos em um banco de dados local utilizando SQLite. O aplicativo oferece funcionalidades para inserir, atualizar, excluir e listar alunos.

## Estrutura do Projeto

### Pacotes Principais

- **Model**: 
  - `br.edu.fatec.bancodedados.model.Aluno`: Representa os dados dos alunos (id, nome, CPF, telefone).

- **DAO**: 
  - `br.edu.fatec.bancodedados.dao.AlunoDAO`: Gerencia as operações de banco de dados (CRUD) para a tabela de alunos.

- **Util**: 
  - `br.edu.fatec.bancodedados.util.ConnectionFactory`: Gerencia a conexão com o banco de dados SQLite e define a estrutura da tabela.

## Funcionalidades

- Cadastrar novos alunos.
- Atualizar informações de alunos existentes.
- Excluir alunos do banco de dados.
- Listar todos os alunos cadastrados.

## Como Executar

1. **Configuração do Ambiente**: 
   - Instale o [Android Studio](https://developer.android.com/studio).

2. **Clonar o Repositório**: 
   ```bash
   git clone https://github.com/AlissonMM/BancoDeDadosAndroid.git
