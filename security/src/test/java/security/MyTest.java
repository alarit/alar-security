package security;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class MyTest {

	@Test
	public void test(){
		PathMatcher pathMacher = new AntPathMatcher();
		
		assertTrue(pathMacher.match("*", "test"));
		assertFalse(pathMacher.match("/whoami", "/whoamiX"));
		assertTrue(pathMacher.match("/whoami/*", "/whoami/pippo"));
		assertFalse(pathMacher.match("/whoami/*", "/whoami/pippo/pluto"));
		assertTrue(pathMacher.match("/whoami/**", "/whoami/pippo/pluto"));
	}
}
