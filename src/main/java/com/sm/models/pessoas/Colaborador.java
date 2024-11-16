package com.sm.models.pessoas;

public class Colaborador {
	private int id;
	private String nome;
	private String dataNascimento;
	private String matricula;
	private String telefone;
	private String telefoneContato;
	private String endereco;
	private String cep;
	private String bu;
	private String setor;
	private String funcao;

  public Colaborador(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Colaborador(String nome){
		this.nome = nome;
	}


  public Colaborador(String nome, String matricula){
		this.nome = nome;
    this.matricula = matricula;
	}


	public Colaborador(String nome, String dataNascimento, String matricula, String telefone, String telefoneContato, String endereco, String cep, String bu, String setor, String funcao) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.matricula = matricula;
		this.telefone = telefone;
		this.telefoneContato = telefoneContato;
		this.endereco = endereco;
		this.cep = cep;
		this.bu = bu;
		this.setor = setor;
		this.funcao = funcao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
}