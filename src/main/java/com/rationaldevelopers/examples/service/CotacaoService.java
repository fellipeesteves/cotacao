/*
 * Copyright 2019 Ryan McGuinness
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rationaldevelopers.examples.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.rationaldevelopers.examples.model.Cotacao;

@Named("cotacaoService")
@Singleton
public class CotacaoService {
	@Inject
	DataService dataService;

	@Transactional
	public List<Cotacao> listByDate(final String date) {
		List<Cotacao> cotacoes = dataService.findByNamedQueryWithParams(Cotacao.class, Cotacao.QRY_FIND_BY_DATE, date);
		
		if (cotacoes.isEmpty()) {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='".concat(date).concat("'&$top=100&$format=json"));
			Cotacao cotacao = target.request(MediaType.APPLICATION_JSON).get(Cotacao.class);
			dataService.save(cotacao);
		}
		
		return dataService.findByNamedQueryWithParams(Cotacao.class, Cotacao.QRY_FIND_BY_DATE, date);
	}

	public void save(Cotacao cotacao) {
		dataService.save(cotacao);
	}
}
