package br.com.cotiinformatica.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="plano")
public class Plano {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idPLano")
	private Integer idPlano;
	
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "descricao", length = 500, nullable = false)
	private String descricao;
	
	@OneToMany //1 Plano para muitos Clientes
	private List<Cliente> clientes;
	
	public Plano() {
		// TODO Auto-generated constructor stub
	}

	public Plano(Integer idPlano, String nome, String descricao, List<Cliente> clientes) {
		super();
		this.idPlano = idPlano;
		this.nome = nome;
		this.descricao = descricao;
		this.clientes = clientes;
	}

	public Integer getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Integer idPlano) {
		this.idPlano = idPlano;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	
	@Override
	public String toString() {
		return "Plano [idPlano=" + idPlano + ", nome=" + nome + ", descricao=" + descricao + ", clientes=" + clientes
				+ "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Plano) {
			Plano plano = (Plano) obj;
			return this.idPlano.equals(plano.getIdPlano());
		}
		
		return false;
	}
	
		@Override
		public int hashCode() {
			return this.idPlano.hashCode();
	}
	
	
}