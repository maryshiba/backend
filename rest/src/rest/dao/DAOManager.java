package rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import rest.bean.Cliente;

/**
 * Session Bean implementation class DAOManager
 */
@Stateless
@LocalBean
public class DAOManager implements DAOManagerLocal {

    public String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10226433";
	//public String url = "jdbc:sqlserver://sql10226433.database.windows.net:1433;database=ecommerce;user=sql10226433@sql10226433;password=l4e3DmcV8L;encrypt=true;trustServerCertificate=false;hostNameInCertificate=brazilsouth1-a.control.database.windows.net;loginTimeout=30;";
	
    public DAOManager() {
    }

    public Cliente autenticar(String email, String senha) {
    	Cliente cliente = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sql10226433", "l4e3DmcV8L");
			Statement stmt = con.createStatement();
			stmt.executeQuery("select * from cliente where email='"+email+"'"+" and senha='"+senha+"'");
			ResultSet res = stmt.getResultSet();
			while (res.next()) {
				cliente = new Cliente();
				cliente.setCpf(res.getString("cpf"));
				cliente.setEmail(res.getString("email"));
				cliente.setSenha(res.getString("senha"));
				cliente.setEndereco(res.getString("endereco"));
				cliente.setEstado(res.getString("estado"));
				cliente.setMunicipio(res.getString("municipio"));
				cliente.setNome(res.getString("nome"));
				cliente.setTelefone(res.getString("telefone"));
				cliente.setPersisted(true);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("ERROR: failed to load mySQL JDBC driver.");
			e.printStackTrace();
		}
		return cliente;
    }
    
    public Cliente consultar(String cpf) {
    	Cliente cliente = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sql10226433", "l4e3DmcV8L");
			Statement stmt = con.createStatement();
			stmt.executeQuery("select * from cliente where cpf='"+cpf+"'");
			ResultSet res = stmt.getResultSet();
			while (res.next()) {
				cliente = new Cliente();
				cliente.setCpf(res.getString("cpf"));
				cliente.setEmail(res.getString("email"));
				cliente.setSenha(res.getString("senha"));
				cliente.setEndereco(res.getString("endereco"));
				cliente.setEstado(res.getString("estado"));
				cliente.setMunicipio(res.getString("municipio"));
				cliente.setNome(res.getString("nome"));
				cliente.setTelefone(res.getString("telefone"));
				cliente.setPersisted(true);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("ERROR: failed to load mySQL JDBC driver.");
			e.printStackTrace();
		}
		return cliente;
    }
    
    public boolean salvar(Cliente cliente) {
    	boolean executed = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "sql10226433", "l4e3DmcV8L");
			Statement stmt = con.createStatement();
			String query = "";
			if (consultar(cliente.getCpf()) != null) {
				query = "update cliente set nome = '"+cliente.getNome()+"', email = '"+cliente.getEmail()+"', senha = '"+cliente.getSenha()+"' where cpf='"+cliente.getCpf()+"'";
			} else {
				query = "insert into cliente (cpf, nome, email, senha) values ('"+cliente.getCpf()+"', '"+cliente.getNome()+"', '"+cliente.getEmail()+"', '"+cliente.getSenha()+"')";
			}
			stmt.executeUpdate(query);
			stmt.close();
			
			executed = true;
		} catch (Exception e) {
			System.out.println("ERROR: failed to load mySQL JDBC driver.");
			e.printStackTrace();
		}
		return executed;
    }
    
    public boolean excluir(String cpf) {
    	boolean executed = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "sql10226433", "l4e3DmcV8L");
			Statement stmt = con.createStatement();
			String query = "delete from cliente where cpf = '" + cpf + "'";
			stmt.executeUpdate(query);
			stmt.close();
			
			executed = true;
		} catch (Exception e) {
			System.out.println("ERROR: failed to load mySQL JDBC driver.");
			e.printStackTrace();
		}
		return executed;
    }
}
