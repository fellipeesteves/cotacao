package com.bb.cotacao.endpoints;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.bb.cotacao.model.Cotacao;
import com.bb.cotacao.service.CotacaoService;

@Path("/cotacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CotacaoEndPoint {
	@Inject
	CotacaoService cotacaoService;

	@GET
	@PermitAll
	@Path("/{date}")
	public Response get(@Context SecurityContext ctx, final @PathParam("date") String date) {
		try {
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			
			Date dateParsed = formatter.parse(date);

			final List<Cotacao> cotacoes = cotacaoService.listByDate(dateParsed);
			
			if (cotacoes.isEmpty()) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			
			return Response.ok(cotacoes.toArray()).build();
		} catch (ParseException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
}
