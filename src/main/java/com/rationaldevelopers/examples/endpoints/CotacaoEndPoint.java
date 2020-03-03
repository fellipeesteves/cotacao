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

package com.rationaldevelopers.examples.endpoints;

import com.rationaldevelopers.examples.model.User;
import com.rationaldevelopers.examples.service.CotacaoService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

@Path("/cotacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CotacaoEndPoint {
  @Inject
  CotacaoService cotacaoService;

  @Inject
  JsonWebToken jwt;

  @POST
  public void authenticate() {

  }

  @PermitAll
  @GET
  @Path("/{date}")
  public Response get(@Context SecurityContext ctx, final @PathParam("date") String date) {
    final Principal caller =  ctx.getUserPrincipal();
    final String name = caller == null ? "anonymous" : caller.getName();
    final List<Cotacao> cotacoes = cotacaoService.listByDate(date);
    return Response.ok(users.toArray()).build();
  }
}
