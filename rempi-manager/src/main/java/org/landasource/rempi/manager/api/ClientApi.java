package org.landasource.rempi.manager.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/api/client")
public interface ClientApi {

	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Path("/{clientId}/state")
	public ClientState getClientState(@PathParam("clientId") final String clientId);

}
