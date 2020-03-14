package com.bb.cotacao.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CotacaoData  {

	@JsonProperty("@odata.context")
	private String context;

	@JsonProperty("value")
	private List<CotacaoValueData> value;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<CotacaoValueData> getValue() {
		return value;
	}

	public void setValue(List<CotacaoValueData> value) {
		this.value = value;
	}

}
