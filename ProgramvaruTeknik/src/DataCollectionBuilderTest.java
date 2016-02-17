import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataCollectionBuilderTest {

	private ImplDataSource dataX;
	private ImplDataSource dataY;
	private DataCollectionBuilder dcBuilder;
	private DataCollection dc;
	private Map<String, MatchedDataPair> map;
	
	@Before
	public void setUp() throws Exception {
		dataX = new ImplDataSource("Temperature", "C");
		dataY = new ImplDataSource("Gaols", "Z+");
		setUpData();
		dcBuilder = new DataCollectionBuilder(dataX, dataY, Resolution.DAY);
	}
	
	private void setUpData() {
		dataX.addData(LocalDate.of(2000, 12, 31), 5d);
		dataX.addData(LocalDate.of(2000, 01, 01), 10d);
		dataX.addData(LocalDate.of(2000, 01, 03), 5d);
		dataX.addData(LocalDate.of(2000, 01, 20), 15d);
		dataX.addData(LocalDate.of(2000, 04, 20), 3d);
		
		dataY.addData(LocalDate.of(2000, 12, 31), 2d);
		dataY.addData(LocalDate.of(2000, 01, 01), 3d);
		dataY.addData(LocalDate.of(2000, 01, 03), 4d);
		dataY.addData(LocalDate.of(2000, 01, 20), 5d);
		dataY.addData(LocalDate.of(2000, 04, 20), 3d);
	}

	/*@After
	public void tearDown() throws Exception {
		dcBuilder = null;
		dataX = null;
		dataY = null;
	}*/


	@Test
	public void testGetResult() {
		// Days
				dc = dcBuilder.getResult();
				map = dc.getData();
				
				MatchedDataPair pair1 = map.get("2000-1-1");
				System.out.println(pair1.getXvalue());
				System.out.println(pair1.getYvalue());
				assertEquals(10d, pair1.getXvalue(), 0.001);
				assertEquals(3d, pair1.getYvalue(), 0.001);
				
				// Months
				dcBuilder.setResolution(Resolution.MONTH);
				dc = dcBuilder.getResult();
				map = dc.getData();
				MatchedDataPair pair2 = map.get("2000-01");
				//System.out.println(pair2);
				//System.out.println(pair2);
				assertEquals(10d, pair2.getXvalue(), 0.001);
				assertEquals(4d, pair2.getYvalue(), 0.001);
	}
}
