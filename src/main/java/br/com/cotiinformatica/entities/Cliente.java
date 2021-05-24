package br.com.cotiinformatica.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcliente")
	private Integer idCliente;

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "cpf", length = 15, nullable = false, unique = true)
	private String cpf;

	@ManyToOne
	@JoinColumn(name = "idplano", nullable = false)
	private Plano plano;

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer idCliente, String nome, String email, String cpf) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + "]";
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Cliente) {
			Cliente cliente = (Cliente) obj;
			return this.idCliente.equals(cliente.getIdCliente());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.idCliente.hashCode();
	}
	
	
}
