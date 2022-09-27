package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/clientes?useTimezone=true&serverTimezona=UTC";
	private String user = "root";
	private String password = "Luizamo9";
	
	
	private Connection conectar() {
		 Connection con = null;
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, user, password);
			 return con;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/** CRUD CREATE**/
	
	public void inserirCliente(JavaBeans cliente) {
		String create = "insert into cliente (nome, email, senha) values (?,?,?) ";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getSenha());
			
			pst.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/** CRUD READ**/
	
	 public ArrayList<JavaBeans> listarClientes() {
			String read = "select * from contatos order by nome";
			ArrayList<JavaBeans> clientes = new ArrayList<>();
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(read);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					String id = rs.getString(1);
					String nome = rs.getString(2);
					String email = rs.getString(3);
					String senha = rs.getString(4);
					clientes.add(new JavaBeans(id,nome,email,senha));
					
					
				}
				con.close();
				return clientes;
			} catch (Exception e) {
				System.out.println(e);
				return null;
		 
	 }
}
	 /** CRUD UPDATE**/
	 public void selecionarCliente (JavaBeans cliente) {
		 String read2 = "select * from cliente where id = ?";
		 
		 try {
			 Connection con = conectar();
			 PreparedStatement pst = con.prepareStatement(read2);
			 pst.setString(1, cliente.getId());
			 ResultSet rs = pst.executeQuery();
			 
			 while (rs.next()) {
				 cliente.setId(rs.getString(1));
				 cliente.setNome(rs.getString(2));
				 cliente.setEmail(rs.getString(3));
				 cliente.setSenha(rs.getString(4));
			 }
			 con.close();
			
			
		} catch (Exception e) {
System.out.println(e);
		}
		 
		 
	 }
	 public void alterarCliente(JavaBeans cliente) {
		 String create = "update cliente set nome=?, email=?, senha=? where id=?";
		 
		 
		 try {
			 Connection con = conectar();
			 PreparedStatement pst = con.prepareStatement(create);
			 pst.setString(1, cliente.getNome());
			 pst.setString(2, cliente.getEmail());
			 pst.setString(3, cliente.getSenha());
			 pst.setString(4, cliente.getId());
			 pst.executeUpdate();
			 con.close();
			
		} catch (Exception e) {
System.out.println(e);
		}
	 }
	 
	 /** CRUD DELETE**/
	 
	 public void deletarCliente(JavaBeans cliente) {
		 String delete = "delete from cliente where id=?";
		 try {
			 Connection con = conectar ();
			 PreparedStatement pst = con.prepareStatement(delete);
			 pst.setString(1, cliente.getId());
			 pst.executeUpdate();
			 con.close();
			
		} catch (Exception e) {
System.out.println(e);
		}
	 }
	 
}
