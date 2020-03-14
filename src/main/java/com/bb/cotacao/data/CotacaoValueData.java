package com.bb.cotacao.data;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CotacaoValueData {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("dataHoraCotacao")
	private Date dataCotacao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonProperty("cotacaoCompra")
	private BigDecimal valorCompra;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonProperty("cotacaoVenda")
	private BigDecimal valorVenda;
	
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

}