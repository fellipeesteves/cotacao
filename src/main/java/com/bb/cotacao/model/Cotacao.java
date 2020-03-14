package com.bb.cotacao.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonRootName("cotacao")
@JsonIgnoreProperties(ignoreUnknown = true)
@RegisterForReflection
@Entity
@Table(name = "cotacao")
@NamedQueries({
		@NamedQuery(name = Cotacao.QRY_FIND_BY_DATE, query = "select c from Cotacao c where DATE(c.dataCotacao) = DATE(?1)") })
public class Cotacao extends Persistent {

	private static final long serialVersionUID = 1L;

	public static final String QRY_FIND_BY_DATE = "Cotacao.findByDate";

	@JsonbProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_generator")
	@SequenceGenerator(name = "cotacao_generator", allocationSize = 50, sequenceName = "seq_cotacao", initialValue = 100)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@JsonbProperty("dataCotacao")
	@JsonbDateFormat(DATE_FORMAT)
	@Column(name = "dataCotacao")
	private Date dataCotacao;

	@JsonbProperty("valorCompra")
	@Column(name = "valorCompra", precision = 5, scale = 4)
	@Type(type = "big_decimal")
	private BigDecimal valorCompra;

	@JsonbProperty("valorVenda")
	@Column(name = "valorVenda", precision = 5, scale = 4)
	@Type(type = "big_decimal")
	private BigDecimal valorVenda;
	
	public Cotacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCotacao() {
		return dataCotacao;
	}

	public void setDataCotacao(Date dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cotacao cotacao = (Cotacao) o;
		return Objects.equals(dataCotacao, cotacao.dataCotacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
