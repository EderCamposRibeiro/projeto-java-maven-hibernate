package projetojavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setLogin("teste deleta 2");
		pessoa.setNome("Deleta");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Para Deletar");
		pessoa.setEmail("deleta@gmail.com");
		
		daoGeneric.salvar(pessoa);
	}
	
	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
		}	
	
	/*Outra forma de testar o pesquisar genérico*/
	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(2L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
		}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(3L, UsuarioPessoa.class);
		
		pessoa.setNome("Nome atualizado Hibernate");
		pessoa.setSenha("4321");

		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
		}	
	
	/*Teste para deletar*/
	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(4L, UsuarioPessoa.class);
		
		daoGeneric.deletarPorId(pessoa);

		}	
	
	/*Teste para consultar lista*/
	@Test
	public void testeConsultarLista() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = daoGeneric.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------------------");
		}

	}
	
	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().
				createQuery(" from UsuarioPessoa where nome like '%Deleta%' ").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------------------");
		}
 		
		
	}
	
	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().
				createQuery(" from UsuarioPessoa order by nome desc ")
				.setMaxResults(3)
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------------------");
		}
	}
	
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.
				getEntityManager().createQuery(" from UsuarioPessoa where nome = :nome ")
				.setParameter("nome", "Eder")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------------------");
		}
		
	}
	
	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGeneric
				.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u ")
				.getSingleResult();
		
		System.out.println("Soma de todas as idades = " + somaIdade);
		
	}
	
	@Test
	public void testeQueryMediaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Double mediaIdade = (Double) daoGeneric
				.getEntityManager().createQuery("select avg(u.idade) from UsuarioPessoa u ")
				.getSingleResult();
		
		System.out.println("Média das idades = " + mediaIdade);
		
	}	
	
	@Test
	public void testeNamedQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric
				.getEntityManager().createNamedQuery("UsuarioPessoa.findAll")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------------------");
		}
		
	}
	
	@Test
	public void testeNamedQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric
				.getEntityManager().createNamedQuery("UsuarioPessoa.findByNome")
				.setParameter("nome", "Deleta")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------------------");
		}
		
	}
	
	

}
