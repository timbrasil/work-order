////VRaptor JPA plugin instead
//
//
//package br.com.timbrasil.operations.interceptor;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//
//import br.com.caelum.vraptor.AroundCall;
//import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
//
//public class TransactionInterceptor {
//	
//	private final EntityManager manager;
//
//	@Deprecated
//	public TransactionInterceptor() {
//		this(null);
//	}
//
//	@Inject
//	public TransactionInterceptor(EntityManager manager) {
//		this.manager = manager;
//	}
//	
//	@AroundCall
//	public void intercept(SimpleInterceptorStack stack){
//		manager.getTransaction().begin();
//		
//		stack.next();
//		
//		manager.getTransaction().commit();
//	}
//	
//	
//
//}
