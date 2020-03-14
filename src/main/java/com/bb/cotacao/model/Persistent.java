package com.bb.cotacao.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@MappedSuperclass
public abstract class Persistent implements Serializable {

	private static final long serialVersionUID = -1437945921305025286L;

	static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	@JsonbProperty("version")
	@Version
	@Column(name = "version", nullable = false)
	private int version;

	@JsonbProperty("dataRegistro")
	@JsonbDateFormat(DATE_FORMAT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataRegistro", nullable = false)
	private Date dataRegistro;

	@JsonbProperty("dataAtualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonbDateFormat(DATE_FORMAT)
	@Column(name = "dataAtualizacao", nullable = false)
	private Date dataAtualizacao;

	public static String getDateFormat() {
		return DATE_FORMAT;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@PrePersist
	public void prePersist() {
		dataRegistro = Calendar.getInstance().getTime();
		dataAtualizacao = Calendar.getInstance().getTime();
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = Calendar.getInstance().getTime();
	}
}
