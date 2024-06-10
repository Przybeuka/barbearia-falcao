package br.com.unicuritiba.barbeariafalcao.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Valor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String corte;
	private String barba;
	private String coloracao;
	private String sombrancelha;
	private String dia;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCorte() {
		return corte;
	}
	public void setCorte(String corte) {
		this.corte = corte;
	}
	public String getBarba() {
		return barba;
	}
	public void setBarba(String barba) {
		this.barba = barba;
	}
	public String getColoracao() {
		return coloracao;
	}
	public void setColoracao(String coloracao) {
		this.coloracao = coloracao;
	}
	public String getSombrancelha() {
		return sombrancelha;
	}
	public void setSombrancelha(String sombrancelha) {
		this.sombrancelha = sombrancelha;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}

	
	
	
}
