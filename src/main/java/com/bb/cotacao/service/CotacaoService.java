package com.bb.cotacao.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.bb.cotacao.data.CotacaoData;
import com.bb.cotacao.data.CotacaoValueData;
import com.bb.cotacao.model.Cotacao;

@Named("cotacaoService")
@Singleton
public class CotacaoService {
	@Inject
	DataService dataService;

	@Transactional
	public List<Cotacao> listByDate(final Date date) throws Exception {
		try {
			List<Cotacao> cotacoes = dataService.findByNamedQueryWithParams(Cotacao.class, Cotacao.QRY_FIND_BY_DATE, date);
	
			if (!cotacoes.isEmpty()) {
				return cotacoes;
			}
	
			String dateFilter = new SimpleDateFormat("MM-dd-yyyy").format(date);
			
			System.out.println(dateFilter);
			
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='" + dateFilter + "'&$top=100&$format=json");
			CotacaoData cotacao = target.request(MediaType.APPLICATION_JSON).get(CotacaoData.class);
			
			if (!cotacao.getValue().isEmpty()) {
				for (CotacaoValueData value : cotacao.getValue()) {
					this.save(value);
				}
				
				return dataService.findByNamedQueryWithParams(Cotacao.class, Cotacao.QRY_FIND_BY_DATE, date);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return new ArrayList<Cotacao>();
	}

	public void save(CotacaoValueData cotacaoValue) {
		Cotacao cotacao = new Cotacao();
		
		cotacao.setDataCotacao(cotacaoValue.getDataCotacao());
		cotacao.setValorCompra(cotacaoValue.getValorCompra());
		cotacao.setValorVenda(cotacaoValue.getValorVenda());
		
		dataService.save(cotacao);
	}
}
