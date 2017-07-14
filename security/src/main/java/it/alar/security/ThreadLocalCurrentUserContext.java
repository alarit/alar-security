package it.alar.security;

public class ThreadLocalCurrentUserContext {
	
	private final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<SecurityContext>();
	
	public void clearContext() {
		contextHolder.remove();
	}

	public SecurityContext getContext() {
		SecurityContext ctx = contextHolder.get();

		if (ctx == null) {
			ctx = createEmptyContext();
			contextHolder.set(ctx);
		}

		return ctx;
	}

	public void setContext(SecurityContext user) {
		if(user == null){
			throw new NullPointerException();
		}
		contextHolder.set(user);
	}

	public SecurityContext createEmptyContext() {
		return new SecurityContext();
	}
}
