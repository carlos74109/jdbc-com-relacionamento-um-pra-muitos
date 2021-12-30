package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ConexaoJdbc.SingleConection;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

public class UserPosDao {

	private Connection connection;

	public UserPosDao() {
		connection = SingleConection.getConnection();
	}

	public void salvar(UserPosJava user) {
		String sql = "insert into userposjava(nome, email) values (?, ?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, user.getNome());
			pst.setString(2, user.getEmail());
			pst.executeUpdate();
			connection.commit();

		} catch (Exception e) {

		}

	}

	public List<UserPosJava> listar() throws Exception {
		List<UserPosJava> list = new ArrayList<UserPosJava>();

		String sql = "select * from userposjava";

		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		UserPosJava userposjava = new UserPosJava();

		while (rs.next()) {
			userposjava.setId(rs.getLong("id"));
			userposjava.setNome(rs.getString("nome"));
			userposjava.setEmail(rs.getString("email"));

			list.add(userposjava);
		}

		return list;

	}

	public UserPosJava buscar(Long id) throws Exception {
		UserPosJava retorno = new UserPosJava();

		String sql = "select * from userposjava where id = " + id;

		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {// retornor apenas ou nenhum
			retorno.setId(rs.getLong("id"));
			retorno.setNome(rs.getString("nome"));
			retorno.setEmail(rs.getString("email"));

		}

		return retorno;

	}

	@Test
	public void initLista() {
		UserPosDao dao = new UserPosDao();
		try {
			List<UserPosJava> list = dao.listar();

			for (UserPosJava userPosJava : list) {
				System.out.println(userPosJava);
				System.out.println("------------------");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void initbuscar() {
		UserPosDao dao = new UserPosDao();

		try {
			UserPosJava user = dao.buscar(5l);

			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void atualizar(UserPosJava user) {
		String sql = "update userposjava set nome = ? where id = " + user.getId();

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, user.getNome());

			pst.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void deletar(Long id) {
		String sql = "delete from userposjava where id = " + id;
		
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void salvarTelefone(Telefone fone) {
		String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values (?, ?, ?)";
		
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, fone.getNumero());
			pst.setString(2, fone.getTipo());
			pst.setLong(3, fone.getUsuario());
			pst.executeUpdate();
			connection.commit();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public List<BeanUserFone> listaUserFone(Long idUser){
		
		List<BeanUserFone> beanUserFone = new ArrayList<BeanUserFone>();
		String sql = " select numero, nome, email from telefoneuser as fone ";
		sql += " inner join userposjava as userp ";
		sql += " on fone.usuariopessoa =userp.id ";
		sql += " where userp.id = " + idUser;
		
		try {
			BeanUserFone userFone = new BeanUserFone();
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()) {
				userFone.setEmail(rs.getString("email"));
				userFone.setNome(rs.getString("nome"));
				userFone.setNumero(rs.getString("numero"));
				
				beanUserFone.add(userFone);
				
			}
			return beanUserFone;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	//Delete em cascata
	public void deleteFonesPorUser(Long id) {
		
		String sqlFone = "delete from telefoneuser where usuariopessoa =" + id;
		String sqlUser = "delete from userposjava where id=" + id;
		
		try {
			PreparedStatement pst = connection.prepareStatement(sqlFone);
			pst.executeUpdate();
			connection.commit();
			
			pst = connection.prepareStatement(sqlUser);
			pst.executeUpdate();
			connection.commit();
			
			System.out.println("a exclus�o foi feita com sucesso");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
