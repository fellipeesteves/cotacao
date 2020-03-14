package com.bb.cotacao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.bb.cotacao.concurrent.ManagedThreadLocal;
import com.bb.cotacao.model.Cotacao;
import com.bb.cotacao.service.CotacaoService;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AbstractTest {
	@Inject
	private CotacaoService cotacaoService;

	@BeforeEach
	public void before() throws Exception {
		List<Cotacao> existing = cotacaoService.listByDate(new Date());
		if (existing.isEmpty()) {
			Cotacao newCotacao = new Cotacao();
			newCotacao.setValorCompra(new BigDecimal(4.4143));
			newCotacao.setValorVenda(new BigDecimal(4.2343));
			newCotacao.setDataCotacao(new Date());
			new ManagedThreadLocal(existing);
		}
	}

	@AfterEach
	public void after() {
	}
}
