package it.alar.security;

public class ContextHolder {
	
	private static ThreadLocalCurrentUserContext threadLocalContext;
	
	static{
		init();
	}
	
	private static void init(){
		threadLocalContext = new ThreadLocalCurrentUserContext();
	}
	
	public static SecurityContext getContext() {
		return threadLocalContext.getContext();
	}
	
	public static void setContext(SecurityContext u){
		threadLocalContext.setContext(u);
	}
}
