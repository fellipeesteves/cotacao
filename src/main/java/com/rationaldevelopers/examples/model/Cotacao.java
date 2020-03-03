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

package com.rationaldevelopers.examples.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

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
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.annotations.RegisterForReflection;

@XmlRootElement(name = "cotacao")
@RegisterForReflection
@Entity
@Table(name="cotacao")
@NamedQueries({
    @NamedQuery(name = Cotacao.QRY_FIND_BY_DATE, query = "select u from Cotacao u where u.dataCotacao=?1"),
})
public class Cotacao extends Persistent {
  private static final Logger LOGGER = LoggerFactory.getLogger(Cotacao.class);
  
  public static final String QRY_FIND_BY_DATE = "Cotacao.findByDate";

  @JsonbProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_generator")
  @SequenceGenerator(name="cotacao_generator", allocationSize=50, sequenceName = "seq_cotacao", initialValue = 100)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @JsonbProperty("dataCotacao")
  @Column(name = "dataCotacao")
  private Date dataCotacao;

  @JsonbProperty("dataRegistro")
  @Column(name = "dataRegistro")
  private Date dataRegistro;

  @JsonbProperty("valorCompra")
  @Column(name = "valorCompra")
  private BigInteger valorCompra;

  @JsonbProperty("valorVenda")
  @Column(name="valorVenda")
  private BigInteger valorVenda;

  public Cotacao() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cotacao cotacao = (Cotacao) o;
    return Objects.equals(dataCotacao, cotacao.dataCotacao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
