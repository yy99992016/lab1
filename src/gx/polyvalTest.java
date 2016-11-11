package gx;

import static org.junit.Assert.*;

import org.junit.Test;

import gx.polyval;

public class polyvalTest {

	@Test
	public void testExpression() {
		polyval  np = new polyval();
		assertEquals(true,np.Derivative("a*b","a"));

	}

}
