package br.com.frederico.curso.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "* Preencha o campo descrição!")
	private String descricao;
	
	@DecimalMin(value = "1", message = "Preencha o valor com no mínimo R$1,00!")
	private Float valor;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Preencha o campo data!")
	private LocalDate data;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contas_categoria_id", referencedColumnName = "id")
	private Categoria categoria;
}
