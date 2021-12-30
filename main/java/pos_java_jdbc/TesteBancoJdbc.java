package pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import DAO.UserPosDao;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

public class TesteBancoJdbc  {
	
	@Test
	public void initBanco() {
		UserPosJava user = new UserPosJava();
		UserPosDao dao = new UserPosDao();
		
		
		user.setEmail("joao@email.com");
		user.setNome("joao");
		
		
		
		dao.salvar(user);
	}
	
	@Test
	public void atualizar () throws Exception {
		
		UserPosDao dao = new UserPosDao();
		
		UserPosJava objeto = dao.buscar(3l);
		
		objeto.setNome("dracula");
		
		dao.atualizar(objeto);
		
	}
	
	@Test
	public void deletar () {
		UserPosDao dao = new UserPosDao();
		dao.deletar(3l);
	}
	
	@Test
	public void inserirTelefone() {
		UserPosDao dao = new UserPosDao();
		Telefone fone = new Telefone();
		
		fone.setNumero("(45) 09 87456612");
		fone.setTipo("telefone");
		fone.setUsuario(7l);
		
		dao.salvarTelefone(fone);
		
	}
	
	@Test
	public void testeCarregarFoneUser () {
		UserPosDao dao = new UserPosDao();
		
		List<BeanUserFone> userFone = dao.listaUserFone(4l);
		
		for (BeanUserFone beanUserFone : userFone) {
			System.out.println(beanUserFone);
			System.out.println("--------------------");
		}
	}
	
	@Test
	public void testeDeleteUserFone() {
		UserPosDao dao = new UserPosDao();
		dao.deleteFonesPorUser(4l);
	}
	
}
