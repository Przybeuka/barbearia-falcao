package br.com.unicuritiba.barbeariafalcao.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Agenda {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		private String nome;
		private String telefone;
		private String dia;
		private String horario;
		private String barbeiro;
		private String servico;
		
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		public String getDia() {
			return dia;
		}
		public void setDia(String dia) {
			this.dia = dia;
		}
		public String getHorario() {
			return horario;
		}
		public void setHorario(String horario) {
			this.horario = horario;
		}
		public String getBarbeiro() {
			return barbeiro;
		}
		public void setBarbeiro(String barbeiro) {
			this.barbeiro = barbeiro;
		}
		public String getServico() {
			return servico;
		}
		public void setServico(String servico) {
			this.servico = servico;
		}
		
}
