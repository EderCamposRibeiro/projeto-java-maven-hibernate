package projetojavamavenhibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setLogin("admin 2");
		pessoa.setNome("Eder 2");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Campos Ribeiro");
		pessoa.setEmail("edertestejava@gmail.com");
		
		daoGeneric.salvar(pessoa);
	}

}
