-- Cria o banco de dados
CREATE DATABASE syncmanager
  WITH
  OWNER = postgres
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1
  IS_TEMPLATE = False;

-- Conecta ao banco de dados syncmanager
\c syncmanager

-- Cria a tabela de componentes
CREATE TABLE componentes (
  id SERIAL PRIMARY KEY,
  nome TEXT NOT NULL,
  ctf TEXT NOT NULL UNIQUE,
  preco REAL NOT NULL,
  descricao TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Cria a tabela de circuitos
CREATE TABLE circuitos (
  id SERIAL PRIMARY KEY,
  nome TEXT NOT NULL,
  ctf TEXT NOT NULL UNIQUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cria a tabela de relação entre circuitos e componentes
CREATE TABLE circuitos_componentes (
  circuito_id INTEGER NOT NULL,
  componente_id INTEGER NOT NULL,
  componente_ctf TEXT NOT NULL,
  circuito_ctf TEXT NOT NULL,
  quantidade INTEGER NOT NULL,
  PRIMARY KEY (circuito_id, componente_id),
  FOREIGN KEY (circuito_id) REFERENCES circuitos(id) ON DELETE CASCADE,
  FOREIGN KEY (componente_id) REFERENCES componentes(id) ON DELETE CASCADE
);