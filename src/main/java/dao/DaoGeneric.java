package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import projetojavamavenhibernate.HibernateUtil;

public class DaoGeneric<E> {
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public void salvar(E entidade) {
		/*Iniciar uma transação*/
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}

	/*Salva ou Atualiza*/
	public E updateMerge(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeSalva = entityManager.merge(entidade);
		transaction.commit();
		
		return entidadeSalva;
	}	
	
	public E pesquisar(E entidade) {
		Object id = HibernateUtil.gerPrimaryKey(entidade);
		
		E e = (E) entityManager.find(entidade.getClass(), id);
		
		return e;
	}
	
	/*Outra forma de fazer o pesquisar genérico*/
	public E pesquisar2(Long id, Class<E> entidade) {
		
		E e = (E) entityManager.find(entidade, id);
		
		return e;
	}
	
	public void deletarPorId(E entidade) {
		
		Object id = HibernateUtil.gerPrimaryKey(entidade);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		/*Evita que vários erros venham a ocorres.
		 *Exemplo: - objeto sendo controlado na sessão
		 *         - objeto com múltiplas sessões
		 *         - cache
		 *->Dessa forma manda o Delete direto para o BD */
		entityManager.createNativeQuery(" delete from " + entidade.getClass().getSimpleName().toLowerCase() + 
				                        " where id =" + id).executeUpdate(); //Faz o delete apesar de ser "update"
		transaction.commit();/*Grava a alteração de forma definitiva no BD*/
		
	}
	
}











