package rest.resource;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rest.bean.Cliente;
import rest.dao.DAOManager;
import rest.dao.DAOManagerLocal;

@Path("cliente") // o @path define a URI do recurso que nesse caso será /helloworld
public class ResourceCliente {
	@EJB
	DAOManagerLocal dao;
	
	@GET @Path("autenticar")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente autenticar(@QueryParam("email")String email, @QueryParam("senha")String senha) {
		System.out.println("AUTENTICAR email: " + email + " | senha: " + senha);
		DAOManager daoM = new DAOManager();
		return daoM.autenticar(email, senha);
	}
	
	@GET @Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente consultar(@QueryParam("cpf")String cpf) {
		System.out.println("CONSULTAR cpf: " + cpf);
		DAOManager daoM = new DAOManager();
		return daoM.consultar(cpf);
	}
	
	@POST @Path("salvar")
	@Consumes(MediaType.APPLICATION_JSON) 
	public void salvar(final Cliente cliente) {
		System.out.println("SALVAR cpf: "+cliente.getCpf());
		DAOManager daoM = new DAOManager();
		daoM.salvar(cliente);
	}
	
	@POST @Path("excluir")
	@Consumes(MediaType.APPLICATION_JSON) 
	public void excluir(final String cpf) {
		System.out.println("EXCLUIR cpf: "+cpf);
		DAOManager daoM = new DAOManager();
		daoM.excluir(cpf);
	}
} 