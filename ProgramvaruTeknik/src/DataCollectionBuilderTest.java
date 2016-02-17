import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataCollectionBuilderTest {

	private ImplDataSource dsX;
	private ImplDataSource dsY;
	private DataCollectionBuilder dcb;
	private DataCollection dc;
	private Map<String, MatchedDataPair> map;
	
	@Before
	public void setUp() throws Exception {
		dsX = new ImplDataSource("Temperature", "C");
		dsY = new ImplDataSource("Gaols", "Z+");
		setUpData();
		dcb = new DataCollectionBuilder(dsX, dsY, Resolution.DAY);
	}
	
	private void setUpData() {
		dsX.addData(LocalDate.of(2000, 12, 31), 5d);
		dsX.addData(LocalDate.of(2000, 01, 01), 10d);
		dsX.addData(LocalDate.of(2000, 01, 03), 5d);
		dsX.addData(LocalDate.of(2000, 01, 20), 15d);
		dsX.addData(LocalDate.of(2000, 04, 20), 3d);
		dsX.addData(LocalDate.of(2001, 11, 10), 10d);
		dsX.addData(LocalDate.of(2002, 10, 11), 3d);
		dsX.addData(LocalDate.of(2002, 05, 01), 20d);
		//unused data
		dsX.addData(LocalDate.of(1998, 10, 11), 35d);
		//weeks
		dsX.addData(LocalDate.of(2016, 02, 16), 10d);
		dsX.addData(LocalDate.of(2016, 02, 17), 10d);
		
		dsY.addData(LocalDate.of(2000, 12, 31), 2d);
		dsY.addData(LocalDate.of(2000, 01, 01), 3d);
		dsY.addData(LocalDate.of(2000, 01, 03), 4d);
		dsY.addData(LocalDate.of(2000, 01, 20), 5d);
		dsY.addData(LocalDate.of(2000, 04, 20), 3d);
		dsY.addData(LocalDate.of(2001, 11, 22), 30d);
		dsY.addData(LocalDate.of(2002, 07, 30), 15d);
		dsY.addData(LocalDate.of(2002, 03, 22), 15d);
		//unused data
		dsY.addData(LocalDate.of(0000, 10, 11), 20d);
		dsY.addData(LocalDate.of(1000, 05, 11), 300d);
		//weeks
		dsY.addData(LocalDate.of(2016, 02, 16), 15d);
		dsY.addData(LocalDate.of(2016, 02, 17), 15d);
	}

	/*@After
	public void tearDown() throws Exception {
		dcBuilder = null;
		dataX = null;
		dataY = null;
	}*/


	@Test
	public void testGetResult() {
		dc = dcb.getResult();
		map = dc.getData();
		MatchedDataPair pair1 = map.get("2000-1-1");
		assertEquals(10d, pair1.getXvalue(), 0.001);
		assertEquals(3d, pair1.getYvalue(), 0.001);
		
		// Months
		dcb.setResolution(Resolution.MONTH);
		dc = dcb.getResult();
		map = dc.getData();
		MatchedDataPair pair2 = map.get("2000-1");
		assertEquals(10d, pair2.getXvalue(), 0.001);
		assertEquals(4d, pair2.getYvalue(), 0.001);
	
		// Year
		dcb.setResolution(Resolution.YEAR);
		dc = dcb.getResult();
		map = dc.getData();
		MatchedDataPair pair3 = map.get("2000");
		assertEquals(7.60, pair3.getXvalue(), 0.001);
		assertEquals(3.40, pair3.getYvalue(), 0.001);
		
		// Weeks
		dcb.setResolution(Resolution.WEEK);
		dc = dcb.getResult();
		map = dc.getData();
		MatchedDataPair pair4 = map.get("2016-7");
		assertEquals(10.00, pair4.getXvalue(), 0.001);
		assertEquals(15.00, pair4.getYvalue(), 0.001);
	}
}
