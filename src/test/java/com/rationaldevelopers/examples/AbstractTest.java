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

package com.rationaldevelopers.examples;

import com.rationaldevelopers.examples.concurrent.ManagedThreadLocal;
import com.rationaldevelopers.examples.model.Cotacao;
import com.rationaldevelopers.examples.service.CotacaoService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import java.util.Optional;

@QuarkusTest
public class AbstractTest {
  @Inject
  private CotacaoService cotacaoService;

  private EntityTransaction transaction = null;

  @BeforeEach
  public void before() throws Exception {
    List<Cotacao> existing = cotacaoService.listByDate("2020-03-03");
    if (!existing.size() <= 0) {
      Cotacao newCotacao = new Cotacao();
      cotacaoService.save(newCotacao);
      new ManagedThreadLocal(Optional.of(newCotacao));
    } else {
      new ManagedThreadLocal(existing);
    }
  }

  @AfterEach
  public void after() {
  }
}
