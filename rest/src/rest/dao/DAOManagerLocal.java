package rest.dao;

import javax.ejb.Local;

import rest.bean.Cliente;

@Local
public interface DAOManagerLocal {
	public Cliente autenticar(String email, String senha);
	public boolean salvar(Cliente cliente);
	public Cliente consultar(String cpf);
}
