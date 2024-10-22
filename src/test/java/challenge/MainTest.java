package test.java.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import main.java.challenge.Main;

class MainTest {

	@Test
	public void q1() throws Exception {
		assertNotEquals(0, new Main().q1());
	}

	@Test
	public void q2() throws Exception {
		assertNotEquals(0, new Main().q2());
	}

	@Test
	public void q3() throws Exception {
		List<String> result = new Main().q3();

		assertNotNull(result);
		assertEquals(20, result.size());
	}

	@Test
	public void q4() throws Exception {
		List<String> result = new Main().q4();

		assertNotNull(result);
		assertEquals(10, result.size());
	}

	@Test
	public void q5() throws Exception {
		List<String> result = new Main().q5();

		assertNotNull(result);
		assertEquals(10, result.size());
	}

	@Test
	public void q6() throws Exception {
		Map<Integer, Integer> result = new Main().q6();

		assertNotNull(result);
		assertNotEquals(0, result.size());
	}

}
