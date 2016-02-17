import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImplDataSourceTest {

	private ImplDataSource dataSource;
	
	@Before
	public void setUp() throws Exception {
		dataSource = new ImplDataSource("Temperature", "C");
	}

	@After
	public void tearDown() throws Exception {
		dataSource = null;
	}

	@Test
	public void testImplDataSource() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		assertEquals("Temperature", dataSource.getName());
	}

	@Test
	public void testAddData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUnit() {
		assertEquals("C", dataSource.getUnit());
	}

	@Test
	public void testGetData() {
		fail("Not yet implemented");
	}

}
