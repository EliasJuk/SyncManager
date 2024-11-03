CREATE DATABASE syncmanager
  WITH
  OWNER = postgres
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1
  IS_TEMPLATE = False;

-- Conecta ao banco de dados syncmanager antes de criar a tabela.
\c syncmanager

CREATE TABLE componentes (
  id SERIAL PRIMARY KEY,
  nome TEXT NOT NULL,
  ctf TEXT NOT NULL UNIQUE,
  preco REAL NOT NULL,
  descricao TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);