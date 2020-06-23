package projetojavamavenhibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*Quando o projeto subir ou quando quisermos fazer alguma operação no banco essa classe Java será chamada*/
/*e ela vai ler o arquivo persistence.xml - deixando instanciada a conexão com o banco de dados*/
public class HibernateUtil {
	
	/*Padrão de aquitetura singleton*/
	/*O Padrão Singleton tem como definição garantir que uma classe tenha apenas uma instância de si mesma e 
	 *que forneça um ponto global de acesso a ela. Ou seja, uma classe gerencia a própria instância dela além
	 * de evitar que qualquer outra classe crie uma instância dela.*/
	/*O projeto não pode ler esse aquivo mais de uma vez*/
	
	public static EntityManagerFactory factory = null;
	
	/*Obriga o init() a sempre ser chamado*/
	static {
		init();
	}
	
	/*Metodo inicial que vai ler o aquivo persistence.xml*/
	private static void init() {
		/*Para identificarmos qualquer erro que possa acontecer vamos envolver em um Try Catch*/
		try {
			/*Verificação para ler o arquivo apenas uma vez*/
			if (factory == null) {
				/*Aqui lemos o persistence-unit do arquivo de configuração(persistence.xml)*/
				factory = Persistence.createEntityManagerFactory("projeto-java-maven-hibernate");
			}
		} catch (Exception e) {
			/*Facilita a identificação da pilha de erro*/
			e.printStackTrace();
		}
	}
	
	/*Retorna o gerenciador de Entidade*/
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();/*Prove a parte de persistência*/
	}
	
	/*Busca a Chave Primária*/
	public static Object gerPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
	
	
}
